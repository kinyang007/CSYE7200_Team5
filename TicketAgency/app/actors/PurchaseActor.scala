package actors

import java.text.SimpleDateFormat

import akka.actor._
import services.PurchaseService

import scala.collection.mutable.ArrayBuffer

object PurchaseActor {
    def props = Props[PurchaseActor]

    case class Purchase(username: String, ticketInfo: String)
    case class Check(check: String)
}

class PurchaseActor extends Actor with ActorLogging {
    import PurchaseActor._

    private val purchaseQueue: ArrayBuffer[Purchase] = ArrayBuffer.empty[Purchase]

    private var startTime: Long = System.currentTimeMillis()
    private var lastTime: Long = System.currentTimeMillis()

    private val df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS")

    private def batchProcess(): Unit = {
        println(s"Last Time: ${df.format(lastTime)}\tStartTime: ${df.format(startTime)}. Start Batch processing...")
        sender() ! PurchaseService.purchase(purchaseQueue.toList)
        purchaseQueue.clear()
        startTime = System.currentTimeMillis()
        println(s"Reset queue. Size: ${purchaseQueue.size}. Start Time: ${df.format(startTime)}")
    }

    override def receive: Receive = {
        case Purchase(username, ticketInfo) =>
            purchaseQueue.append(Purchase(username, ticketInfo))
            lastTime = System.currentTimeMillis()
            println(s"Received. User Name: $username\tTicket Info: $ticketInfo\tTime: ${df.format(lastTime)}")
            if (lastTime - startTime > 10) {
                // TODO - Batch processing (How to return the result signal for each message)
                batchProcess()
            } else {
                sender() ! true
            }
        case Check(check) =>
            println(s"$check")
            lastTime = System.currentTimeMillis()
            if (lastTime - startTime > 10) {
                batchProcess()
            }
    }
}
