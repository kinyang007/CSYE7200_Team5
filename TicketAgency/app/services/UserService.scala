package services

import akka.NotUsed
import akka.actor.ActorSystem
import akka.stream.Materializer
import akka.stream.alpakka.mongodb.scaladsl.MongoSource
import akka.stream.scaladsl.{Sink, Source}
import daos.UserDao
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

        Await.result(rows, 1000 millis)
    }
}
