package services

import akka.{Done, NotUsed}
import akka.actor.ActorSystem
import akka.stream.Materializer
import akka.stream.alpakka.mongodb.DocumentUpdate
import akka.stream.alpakka.mongodb.scaladsl.MongoSink
import akka.stream.alpakka.mongodb.scaladsl.MongoSource
import akka.stream.scaladsl.{Sink, Source}
import daos.UserDao
import org.mongodb.scala.model.Filters
import org.mongodb.scala.model.Updates
import pojos.User

import scala.concurrent._
import scala.concurrent.duration._
import scala.language.postfixOps

object UserService {
    implicit val actorSystem: ActorSystem = ActorSystem()
    implicit val mat: Materializer = Materializer(actorSystem)

    def userLogin(name: String, password: String): Seq[User] = {
        val source: Source[User, NotUsed] = MongoSource(UserDao.findByLogin(name, password))

        val rows: Future[Seq[User]] = source.runWith(Sink.seq)

        Await.result(rows, 5 seconds)
    }

    def findAll: Seq[User] = {
        val source: Source[User, NotUsed] = MongoSource(UserDao.findAll)

        val rows: Future[Seq[User]] = source.runWith(Sink.seq)

        Await.result(rows, 10 minute)
    }

    def findByName(name: String): Seq[User] = {
        val source: Source[User, NotUsed] = MongoSource(UserDao.findByName(name))

        val rows: Future[Seq[User]] = source.runWith(Sink.seq)

        Await.result(rows, 5 seconds)
    }

    def updateUser(user: User): Done = {
        val source: Source[User, NotUsed]#Repr[DocumentUpdate] = Source(Seq(user)).map(
            user => DocumentUpdate(
                filter = Filters.eq("_id", user._id),
                update = Updates.combine(
                    Updates.set("name", user.name),
                    Updates.set("password", user.password),
                    Updates.set("tickets", user.tickets)
                )
            )
        )

        val completion: Future[Done] = source.runWith(MongoSink.updateOne(UserDao.usersCollection))

        Await.result(completion, 5 seconds)
    }
}
