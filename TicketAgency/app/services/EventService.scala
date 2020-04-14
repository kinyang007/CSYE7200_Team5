package services

import akka.{Done, NotUsed}
import akka.actor.ActorSystem
import akka.stream.Materializer
import akka.stream.alpakka.mongodb.DocumentUpdate
import akka.stream.alpakka.mongodb.scaladsl.MongoSink
import akka.stream.alpakka.mongodb.scaladsl.MongoSource
import akka.stream.scaladsl.{Sink, Source}
import daos.EventDao
import org.mongodb.scala.model.{Filters, Updates}
import pojos.Event

import scala.concurrent._
import scala.concurrent.duration._
import scala.language.postfixOps

object EventService {
    implicit val actorSystem: ActorSystem = ActorSystem()
    implicit val mat: Materializer = Materializer(actorSystem)

    def findAll: Seq[Event] = {
        val source: Source[Event, NotUsed] = MongoSource(EventDao.findAll)

        val rows: Future[Seq[Event]] = source.runWith(Sink.seq)

        Await.result(rows, 5 seconds)
    }

    def findByName(name: String): Seq[Event] = {
        val source: Source[Event, NotUsed] = MongoSource(EventDao.findByName(name))

        val rows: Future[Seq[Event]] = source.runWith(Sink.seq)

        Await.result(rows, 20 seconds)
    }

    def findByType(event_type: String): Seq[Event] = {
        val source: Source[Event, NotUsed] = MongoSource(EventDao.findByType(event_type))

        val rows: Future[Seq[Event]] = source.runWith(Sink.seq)

        Await.result(rows, 5 seconds)
    }

    def updateEvent(event: Event): Done = {
        val source: Source[Event, NotUsed]#Repr[DocumentUpdate] = Source(Seq(event)).map(
            event => DocumentUpdate(
                filter = Filters.eq("_id", event._id),
                update = Updates.combine(
                    Updates.set("name", event.name),
                    Updates.set("event_type", event.event_type),
                    Updates.set("rest_tickets", event.rest_tickets),
                    Updates.set("tickets", event.tickets)
                )
            )
        )

        val completion = source.runWith(MongoSink.updateOne(EventDao.eventsCollection))

        Await.result(completion, 5 seconds)
    }
}
