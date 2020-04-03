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

    def userLogin(name: String, password: String): Boolean = {
        val source: Source[User, NotUsed] = MongoSource(UserDao.findByLogin(name, password))

        val rows: Future[Seq[User]] = source.runWith(Sink.seq)

        val result: Seq[User] = Await.result(rows, 5 seconds)

        if (result.isEmpty) false else true
    }
}
