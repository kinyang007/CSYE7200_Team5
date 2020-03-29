package services

import akka.NotUsed
import akka.actor.ActorSystem
import akka.stream.Materializer
import akka.stream.alpakka.mongodb.scaladsl.MongoSource
import akka.stream.scaladsl.{Sink, Source}
import daos.UserDao
import pojos.User

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.{Failure, Success}

class UserService {
    implicit val actorSystem: ActorSystem = ActorSystem()
    implicit val mat: Materializer = Materializer(actorSystem)

    val source: Source[User, NotUsed] = MongoSource(UserDao.findByLogin("user00001", "123456"))

    val rows: Future[Seq[User]] = source.runWith(Sink.seq)

//    rows onComplete {
//        case Success(result) => println(result)
//        case Failure(exception) => println("error: " + exception.getMessage)
//    }
}
