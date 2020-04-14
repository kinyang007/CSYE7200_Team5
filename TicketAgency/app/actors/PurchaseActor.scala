package actors

import akka.actor._
import services.PurchaseService

object PurchaseActor {
    def props = Props[PurchaseActor]

    case class Purchase(username: String, ticketInfo: String)
}

class PurchaseActor extends Actor {

    import PurchaseActor._

    override def receive: Receive = {
        case Purchase(username, ticketInfo) =>
            sender() ! PurchaseService.purchase(username, ticketInfo)
    }
}
