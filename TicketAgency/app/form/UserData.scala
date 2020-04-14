package form

import play.api.data.Forms._
import play.api.data._


case class UserData(name: String, password: String)

object UserData{
  val userForm = Form(
    mapping(
      "name" -> text,
      "password" -> text
    )(UserData.apply)(UserData.unapply)
  )
}

