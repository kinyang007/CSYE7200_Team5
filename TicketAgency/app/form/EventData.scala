package form

import play.api.data.Forms._
import play.api.data._

case class EventData(eventName: String)

object EventData {
  val ownerForm = Form(
    mapping(
      "Event Name" -> text
    )(EventData.apply)(EventData.unapply)
  )
}
