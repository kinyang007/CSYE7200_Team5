package daos

import akka.NotUsed
import akka.actor.ActorSystem
import akka.stream.Materializer
import akka.stream.alpakka.mongodb.scaladsl.MongoSource
import akka.stream.scaladsl.{Sink, Source}

import org.scalatest._
import org.scalatest.time.{Millis, Seconds, Span}
import org.scalatest.concurrent.ScalaFutures
import akka.stream.testkit.scaladsl.StreamTestKit.assertAllStagesStopped

import scala.concurrent._

import pojos._


class UserDaoSpec
    extends FlatSpec
    with Matchers
    with ScalaFutures {

    implicit val defaultPatience: PatienceConfig =
        PatienceConfig(timeout = Span(5, Seconds), interval = Span(500, Millis))

    implicit val actorSystem: ActorSystem = ActorSystem()
    implicit val mat: Materializer = Materializer(actorSystem)

    "UserDaoSpec" should "find one user through login info" in assertAllStagesStopped {
        val source: Source[User, NotUsed] =
            MongoSource(UserDao.findByLogin("user00001", "123456"))

        val users: Future[Seq[User]] = source.runWith(Sink.seq)

        users.futureValue.head.name shouldBe "user00001"
        users.futureValue.head.password shouldBe "123456"
    }

    it should "find one user through name" in assertAllStagesStopped {
        val source: Source[User, NotUsed] =
            MongoSource(UserDao.findByName("user00000"))

        val users: Future[Seq[User]] = source.runWith(Sink.seq)

        users.futureValue.head.name shouldBe "user00000"
    }
}
