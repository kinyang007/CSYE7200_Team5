package controllers

import actors.{EventActor, UserActor}
import actors.UserActor.{FindByName, LoginInfo, UpdateUser}
import akka.actor.ActorSystem
import akka.util.Timeout
import form.UserData
import javax.inject._
import play.api.mvc._
import pojos.{Event, User}

import scala.language.postfixOps
import scala.concurrent.{Await, Future}

@Singleton
class  HomeController @Inject()(system: ActorSystem, cc: ControllerComponents) extends AbstractController(cc) with play.api.i18n.I18nSupport {
    val userActor = system.actorOf(UserActor.props, "user-actor")
    val eventActor = system.actorOf(EventActor.props, "event-actor")

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
        val userList = Await.result(result,5 seconds)
        if(!userList.isEmpty)
            Ok(views.html.userPage(userList.head)).withSession("test" -> "123123123")
        else
            Ok(views.html.userLogin(UserData.userForm))
    }

    def userPurchase(name: String) = Action { implicit request: Request[AnyContent] =>
        Ok(views.html.userPurchase(name))
    }

    def purchaseResult(userName: String, ticketInfo: String) = Action { implicit request: Request[AnyContent] =>
        val ticketData = ticketInfo.split(",")
        val eventName = ticketData.head
        val ticketType = ticketData.tail.head

        val userResult: Future[Seq[User]] = (userActor ? FindByName(userName)).mapTo[Seq[User]]
        val user = Await.result(userResult,5 seconds).head

        val eventResult: Future[Seq[Event]] = (eventActor ? FindByName(eventName)).mapTo[Seq[Event]]
        val event = Await.result(eventResult,30 seconds).head

        val tickets = event.tickets.filter(t => t.ticket_type == ticketType)
        val newUserTickets = user.tickets :+ tickets.head
        val newUser = new User(user._id,user.name,user.password, newUserTickets)
        userActor ? UpdateUser(newUser)
        Ok(views.html.purchaseResult(userName, ticketInfo))
    }

    def owner() = Action { implicit request: Request[AnyContent] =>
        Ok(views.html.owner())
    }


}