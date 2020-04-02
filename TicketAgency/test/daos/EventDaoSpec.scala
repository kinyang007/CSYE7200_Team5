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

class EventDaoSpec
    extends FlatSpec
    with Matchers
    with ScalaFutures {

    implicit val defaultPatience: PatienceConfig =
        PatienceConfig(timeout = Span(5, Seconds), interval = Span(500, Millis))

    implicit val actorSystem: ActorSystem = ActorSystem()
    implicit val mat: Materializer = Materializer(actorSystem)

    "EventDaoSpec" should "find all events" in assertAllStagesStopped {
        val source: Source[Event, NotUsed] = MongoSource(EventDao.findAll)

        val rows: Future[Seq[Event]] = source.runWith(Sink.seq)

        val events = rows.futureValue

        events.head.name shouldBe "TD Garden"
        events.head.`type` shouldBe "Ball Game"
        events.head.rest_tickets shouldBe Map(
            "vip" -> 50,
            "floor1" -> 5000,
            "floor2" -> 10000,
            "floor3" -> 15000,
            "balcony" -> 29950
        )
        events.head.tickets.size shouldBe 50000
    }

    it should "find events by name" in assertAllStagesStopped {
        val source: Source[Event, NotUsed] = MongoSource(EventDao.findByName("Boston Symphony Orchestra"))

        val rows: Future[Seq[Event]] = source.runWith(Sink.seq)

        val events = rows.futureValue

        events.head.name shouldBe "Boston Symphony Orchestra"
        events.head.`type` shouldBe "Concert"
        events.head.rest_tickets shouldBe Map(
            "vip" -> 50,
            "floor1" -> 4000,
            "floor2" -> 6000,
            "balcony" -> 9950
        )
        events.head.tickets.size shouldBe 20000
    }

    it should "find events by type" in assertAllStagesStopped {
        val source: Source[Event, NotUsed] = MongoSource(EventDao.findByType("Exhibition"))

        val rows: Future[Seq[Event]] = source.runWith(Sink.seq)

        val events = rows.futureValue

        events.head.name shouldBe "Museum of Fine Arts"
        events.head.`type` shouldBe "Exhibition"
        events.head.rest_tickets shouldBe Map(
            "vip" -> 50,
            "standard" -> 950
        )
        events.head.tickets.size shouldBe 1000
    }
}
