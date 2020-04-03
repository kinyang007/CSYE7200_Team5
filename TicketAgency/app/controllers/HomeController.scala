package controllers


import actors.UserLoginActor.LoginInfo
import javax.inject._
import play.api.data._
import play.api.data.Forms._
import play.api.mvc._
import services.UserService

@Singleton
class  HomeController @Inject()(cc: ControllerComponents) extends AbstractController(cc) {

    def index() = Action { implicit request: Request[AnyContent] =>
        Ok(views.html.index())
    }

    def userLogin() = Action { implicit request: Request[AnyContent] =>
        Ok(views.html.userLogin(UserData.userForm))
    }

    def userPage() = Action { implicit request: Request[AnyContent] =>
        val formData : UserData = UserData.userForm.bindFromRequest().get
        if (UserService.userLogin(formData.name, formData.password))
          Ok(views.html.userPage(formData))
        else
          Ok(views.html.userLogin(UserData.userForm))
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