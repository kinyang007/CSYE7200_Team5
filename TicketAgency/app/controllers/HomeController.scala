package controllers


import javax.inject._
import play.api.data._
import play.api.data.Forms._
import play.api.mvc._

/**
 * This controller creates an `Action` to handle HTTP requests to the
 * application's home page.
 */
@Singleton
class  HomeController @Inject()(val cc: MessagesControllerComponents) extends MessagesAbstractController(cc){

  /**
   * Create an Action to render an HTML page.
   *
   * The configuration in the `routes` file means that this method
   * will be called when the application receives a `GET` request with
   * a path of `/`.
   */
  def index() = Action { implicit request: Request[AnyContent] =>
    Ok(views.html.index())
  }

  def userLogin() = Action { implicit request: MessagesRequest[AnyContent] =>
    Ok(views.html.userLogin(UserData.userForm))
  }

  def userPage()= Action { implicit request: Request[AnyContent] =>
    val formData : UserData = UserData.userForm.bindFromRequest().get
    Ok(views.html.userPage(formData))
  }

  def owner() = Action { implicit request: Request[AnyContent] =>
    Ok(views.html.owner())
  }

}

case class UserData(name: String, password: String)

object UserData{
  val userForm = Form(
    mapping(
      "name" -> text,
      "password" -> text
    )(UserData.apply)(UserData.unapply)
  )
}