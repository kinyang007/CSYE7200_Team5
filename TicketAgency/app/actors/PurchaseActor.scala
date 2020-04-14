package actors

import akka.actor._
import services.PurchaseService

import scala.collection.mutable.ArrayBuffer

object PurchaseActor {
    def props = Props[PurchaseActor]

    case class Purchase(username: String, ticketInfo: String)
}

class PurchaseActor extends Actor {
    import PurchaseActor._

    private val purchaseQueue: ArrayBuffer[Purchase] = ArrayBuffer.empty[Purchase]

    private var startTime: Long = System.currentTimeMillis()
    private var lastTime: Long = System.currentTimeMillis()

    override def receive: Receive = {
        case Purchase(username, ticketInfo) =>
            purchaseQueue.append(Purchase(username, ticketInfo))
            lastTime = System.currentTimeMillis()
            if (lastTime - startTime > 2000) {
                // TODO - Batch processing (How to return the result signal for each message)
                sender() ! PurchaseService.purchase(purchaseQueue.toList)
                purchaseQueue.clear()
                startTime = System.currentTimeMillis()
            }
    }
}
