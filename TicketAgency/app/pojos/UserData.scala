package pojos

import play.api.data._
import play.api.data.Forms._


case class UserData(name: String, password: String)

object UserData{
  val userForm = Form(
    mapping(
      "name" -> text,
      "password" -> text
    )(UserData.apply)(UserData.unapply)
  )
}

