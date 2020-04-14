package services

import actors.PurchaseActor.Purchase
import akka.actor.ActorSystem
import akka.stream.Materializer
import akka.stream.alpakka.mongodb.DocumentUpdate
import akka.stream.alpakka.mongodb.scaladsl.MongoSink
import akka.stream.scaladsl.Source
import daos.{EventDao, UserDao}
import org.mongodb.scala.model.{Filters, Updates}
import pojos.{Event, Ticket, User}

import scala.concurrent._
import scala.concurrent.duration._
import scala.language.postfixOps

object PurchaseService {
    implicit val actorSystem: ActorSystem = ActorSystem()
    implicit val mat: Materializer = Materializer(actorSystem)

    // Single purchase
    def purchase(username: String, ticketInfo: String): Boolean = {
        val ticketData = ticketInfo.split(",")
        val eventName = ticketData.head
        val ticketType = ticketData.tail.head

        val user = UserService.findByName(username).head
        val event = EventService.findByName(eventName).head

        val tickets = event.tickets.filter(t => t.ticket_type == ticketType && !t.sold)
        if (tickets.nonEmpty) {
            val ticket = tickets.head
            val ticketIndex = event.tickets.indexOf(ticket)

            val newUserTickets = user.tickets :+ Ticket(ticket.ticket_id, ticket.ticket_type, ticket.price, sold = true)
            val newUser = User(user._id,user.name,user.password, newUserTickets)

            val mRest = scala.collection.mutable.Map(event.rest_tickets.toSeq:_*)
            mRest.put(ticketType, mRest.get(ticketType).get - 1)
            val newEventRest = mRest.toMap
            val newEventTickets = event.tickets.updated(ticketIndex, Ticket(ticket.ticket_id, ticket.ticket_type, ticket.price, sold = true))
            val newEvent =  Event(event._id, event.name, event.event_type, newEventRest, newEventTickets)

            val userSource = Source(Seq(newUser)).map(
                user => DocumentUpdate(
                    filter = Filters.eq("_id", user._id),
                    update = Updates.combine(
                        Updates.set("name", user.name),
                        Updates.set("password", user.password),
                        Updates.set("tickets", user.tickets)
                    )
                )
            )

            val eventSource = Source(Seq(newEvent)).map(
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
            val completion0 = eventSource.runWith(MongoSink.updateOne(EventDao.eventsCollection))
            Await.result(completion0, 1 minute)
            val completion1 = userSource.runWith(MongoSink.updateOne(UserDao.usersCollection))
            Await.result(completion1, 1 minute)
            
            val userResult = UserService.findByName(newUser.name).head
            val eventResult = EventService.findByName(newEvent.name).head

            userResult == newUser && eventResult == newEvent
        } else {
            false
        }
    }

    def purchase(purchases: List[Purchase]): Boolean = {
        // TODO - update several purchases

    }
}
