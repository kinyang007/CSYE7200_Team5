package actors

import akka.actor._
import services._
import pojos._

object EventActor {
    def props = Props[EventActor]

    case object FindAllEvent
    case class FindByEventName(name: String)
    case class FindByEventType(event_type: String)
    case class UpdateEvent(event: Event)
}

class EventActor extends Actor {
    import EventActor._

    override def receive: Receive = {
        case FindByEventName(name) =>
            sender() ! EventService.findByName(name)
        case FindByEventType(event_type) =>
            sender() ! EventService.findByType(event_type)
        case UpdateEvent(event) =>
            sender() ! EventService.updateEvent(event)
        case FindAllEvent =>
            sender() ! EventService.findAll
    }
}
