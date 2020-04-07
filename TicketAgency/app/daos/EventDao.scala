package daos

import com.mongodb.reactivestreams.client.{FindPublisher, MongoCollection}
import org.bson.Document
import org.bson.codecs.configuration.CodecRegistries.{fromProviders, fromRegistries}
import org.mongodb.scala.MongoClient.DEFAULT_CODEC_REGISTRY
import org.mongodb.scala.bson.codecs.Macros._
import org.mongodb.scala.model.Filters
import org.mongodb.scala.model.Updates._
import org.mongodb.scala.result.UpdateResult
import org.reactivestreams.Publisher
import pojos._

object EventDao extends DbConfig {

    private val codecRegistry = fromRegistries(fromProviders(classOf[Ticket]), fromProviders(classOf[Event]), DEFAULT_CODEC_REGISTRY)

    val eventsCollection: MongoCollection[Event] = db.getCollection("events", classOf[Event]).withCodecRegistry(codecRegistry)

    def findAll: FindPublisher[Event] = eventsCollection.find()

    def findByName(name: String): FindPublisher[Event] =
        eventsCollection.find(Filters.eq("name", name))

    def findByType(event_type: String): FindPublisher[Event] =
        eventsCollection.find(Filters.eq("type", event_type))

    def modifyTicketStatus(event: Event): Publisher[UpdateResult] =
        eventsCollection.updateOne(Filters.eq("_id", event._id),
            combine(
                set("rest_tickets", event.rest_tickets),
                set("tickets", event.tickets)
            )
        )

}
