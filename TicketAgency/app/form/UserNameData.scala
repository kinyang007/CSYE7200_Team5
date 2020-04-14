package form

import play.api.data.Forms._
import play.api.data._

case class UserNameData(username: String)
object UserNameData {
  val userNameForm = Form(
    mapping(
      "User Name" -> text
    )(UserNameData.apply)(UserNameData.unapply)
  )
}