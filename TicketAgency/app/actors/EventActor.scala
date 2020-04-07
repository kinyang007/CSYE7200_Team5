package actors

import akka.actor._
import services._
import pojos._

object EventActor {
    def props = Props[EventActor]

    case object FindAll
    case class FindByName(name: String)
    case class FindByType(event_type: String)
    case class UpdateEvent(event: Event)
}

class EventActor extends Actor {
    import EventActor._

    override def receive: Receive = {
        case FindByName(name) =>
            sender() ! EventService.findByName(name)
        case FindByType(event_type) =>
            sender() ! EventService.findByType(event_type)
        case UpdateEvent(event) =>
            sender() ! EventService.updateEvent(event)
        case FindAll =>
            sender() ! EventService.findAll
    }
}
