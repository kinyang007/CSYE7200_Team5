package controllers

import actors.UserActor
import actors.UserActor.LoginInfo
import akka.actor.ActorSystem
import akka.util.Timeout
import form.UserData
import javax.inject._
import play.api.mvc._
import pojos.User

import scala.language.postfixOps
import scala.concurrent.{Await, Future}

@Singleton
class  HomeController @Inject()(system: ActorSystem, cc: ControllerComponents) extends AbstractController(cc) with play.api.i18n.I18nSupport {
    val userActor = system.actorOf(UserActor.props, "userlogin-actor")

    import akka.pattern.ask

    import scala.concurrent.duration._
    implicit val timeout: Timeout = 5.seconds

    def index() = Action { implicit request: Request[AnyContent] =>
        Ok(views.html.index())
    }

    def userLogin() = Action { implicit request: Request[AnyContent] =>
        Ok(views.html.userLogin(UserData.userForm))
    }

    def userPage() = Action { implicit request: Request[AnyContent] =>
        val formData : UserData = UserData.userForm.bindFromRequest().get
        val result: Future[Seq[User]] = (userActor ? LoginInfo(formData.name,formData.password)).mapTo[Seq[User]]
        val userList = Await.result(result,1000 millis)
        if(!userList.isEmpty)
            Ok(views.html.userPage(userList.head)).withSession("test" -> "123123123")
        else
            Ok(views.html.userLogin(UserData.userForm))
    }

    def userPurchase(name: String) = Action { implicit request: Request[AnyContent] =>
        Ok(views.html.userPurchase(name))
    }

    def purchaseResult(user: String, ticket: String) = Action { implicit request: Request[AnyContent] =>
        val ticketData = ticket.split(",")
        val event = ticketData.head
        val ticketType = ticketData.tail.head
        Ok(views.html.purchaseResult(user, ticket))
    }

    def owner() = Action { implicit request: Request[AnyContent] =>
        Ok(views.html.owner())
    }


}