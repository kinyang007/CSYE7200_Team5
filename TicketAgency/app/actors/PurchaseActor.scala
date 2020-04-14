package actors

import java.text.SimpleDateFormat

import akka.actor._
import services.PurchaseService
import akka.event.{Logging, LoggingAdapter}

import scala.collection.mutable.ArrayBuffer

object PurchaseActor {
    def props = Props[PurchaseActor]

    case class Purchase(username: String, ticketInfo: String)
}

class PurchaseActor extends Actor with ActorLogging {
    import PurchaseActor._

    private val purchaseQueue: ArrayBuffer[Purchase] = ArrayBuffer.empty[Purchase]

    private var startTime: Long = System.currentTimeMillis()
    private var lastTime: Long = System.currentTimeMillis()

    private val df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS")

    override def receive: Receive = {
        case Purchase(username, ticketInfo) =>
            purchaseQueue.append(Purchase(username, ticketInfo))
            lastTime = System.currentTimeMillis()
            println(s"Received. User Name: $username\tTicket Info: $ticketInfo\tTime: ${df.format(lastTime)}")
//            log.info(s"Received. User Name: $username\tTicket Info: $ticketInfo\tTime: $lastTime")
            if (lastTime - startTime > 2000) {
                // TODO - Batch processing (How to return the result signal for each message)
                println(s"Last Time: ${df.format(lastTime)}\tStartTime: ${df.format(startTime)}. Start Batch processing...")
//                log.info(s"Last Time: $lastTime\tStartTime: $startTime. Start Batch processing...")
                sender() ! PurchaseService.purchase(purchaseQueue.toList)
                purchaseQueue.clear()
                startTime = System.currentTimeMillis()
                println(s"Reset queue. Size: ${purchaseQueue.size}. Start Time: ${df.format(startTime)}")
//                log.info(s"Reset queue. Size: ${purchaseQueue.size}. Start Time: $startTime")
            }
    }
}
