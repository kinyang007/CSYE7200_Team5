package controllers

import actors.EventActor.FindByEventName
import actors.PurchaseActor.{Check, Purchase}
import actors.UserActor.{FindByUserName, LoginInfo}
import actors.{EventActor, PurchaseActor, UserActor}
import akka.actor.ActorSystem
import akka.pattern.ask
import akka.util.Timeout
import form.{EventData, UserData, UserNameData}
import javax.inject._
import play.api.mvc._
import pojos.{Event, User}

import scala.concurrent.duration._
import scala.concurrent.{Await, ExecutionContext, Future}
import scala.language.postfixOps

@Singleton
class  HomeController @Inject()(system: ActorSystem, cc: ControllerComponents)
                               (implicit executionContext: ExecutionContext) extends AbstractController(cc) with play.api.i18n.I18nSupport {
    val userActor = system.actorOf(UserActor.props, "user-actor")
    val eventActor = system.actorOf(EventActor.props, "event-actor")
    val purchaseActor = system.actorOf(PurchaseActor.props, "purchase-actor")

    system.scheduler.scheduleAtFixedRate(
        initialDelay = 0 microseconds,
        interval = 2 second,
        receiver = purchaseActor,
        message = Check("check")
    )

    implicit val timeout: Timeout = 5 minutes

    def index() = Action { implicit request: Request[AnyContent] =>
        Ok(views.html.index())
    }

    def userLogin() = Action { implicit request: Request[AnyContent] =>
        Ok(views.html.userLogin(UserData.userForm))
    }

    def userPage() = Action { implicit request: Request[AnyContent] =>
      val postVals = request.body.asFormUrlEncoded
      postVals.map{ args =>
          val name = args("name").head
          val password = args("password").head
          val result: Future[Seq[User]] = (userActor ? LoginInfo(name,password)).mapTo[Seq[User]]
          val userList = Await.result(result, 5 seconds)
          if(!userList.isEmpty)
              Ok(views.html.userPage(userList.head))
          else
              Ok(views.html.userLogin(UserData.userForm))
      }.getOrElse{
          Ok(views.html.userLogin(UserData.userForm))
      }
    }

    def userPurchase(name: String) = Action { implicit request: Request[AnyContent] =>
        Ok(views.html.userPurchase(name))
    }

    def purchaseResult(userName: String, ticketInfo: String) = Action { implicit request: Request[AnyContent] =>
        // TODO - implement batch purchase

        val result = (purchaseActor ? Purchase(userName, ticketInfo)).mapTo[Boolean]

        if (Await.result(result, 5 minutes)) {
            Ok(views.html.purchaseResult(userName, ticketInfo))
        } else {
            Ok(views.html.userPurchase(userName))
        }

//        val ticketData = ticketInfo.split(",")
//        val eventName = ticketData.head
//        val ticketType = ticketData.tail.head
//
//        val userResult: Future[Seq[User]] = (userActor ? FindByUserName(userName)).mapTo[Seq[User]]
//        val user = Await.result(userResult, 5 seconds).head
//
//        val eventResult: Future[Seq[Event]] = (eventActor ? FindByEventName(eventName)).mapTo[Seq[Event]]
//        val event = Await.result(eventResult, 5 seconds).head
//
//        val tickets = event.tickets.filter(t => t.ticket_type == ticketType && t.sold==false)
//        if(!tickets.isEmpty){
//            val ticket = tickets.head
//            val ticketIndex = event.tickets.indexOf(ticket)
//
//            val newUserTickets = user.tickets :+ new Ticket(ticket.ticket_id,ticket.ticket_type,ticket.price,true)
//            val newUser = new User(user._id,user.name,user.password, newUserTickets)
//
//            val mRest = scala.collection.mutable.Map(event.rest_tickets.toSeq:_*)
//            mRest.put(ticketType, mRest.get(ticketType).get-1)
//            val newEventRest = mRest.toMap
//            val newEventTickets = event.tickets.updated(ticketIndex, new Ticket(ticket.ticket_id,ticket.ticket_type,ticket.price,true))
//            val newEvent = new Event(event._id, event.name, event.event_type, newEventRest, newEventTickets)
//
//            userActor ? UpdateUser(newUser)
//            eventActor ? UpdateEvent(newEvent)
//            Ok(views.html.purchaseResult(userName, ticketInfo))
//        }else
//            Ok(views.html.userPurchase(userName))

    }

    def owner() = Action { implicit request: Request[AnyContent] =>
        Ok(views.html.owner(UserData.userForm))
    }

    def ownerCheckEvent() = Action { implicit request: Request[AnyContent] =>
        Ok(views.html.ownerCheckEvent(EventData.ownerForm, UserNameData.userNameForm))

    }

    def ownerEventList() = Action { implicit request: Request[AnyContent] =>
        val formData : EventData = EventData.ownerForm.bindFromRequest().get
        val eventResult: Future[Seq[Event]] = (eventActor ? FindByEventName(formData.eventName)).mapTo[Seq[Event]]
        val event = Await.result(eventResult, 5 seconds)
        if (!event.isEmpty)
            Ok(views.html.ownerEventList(event.head))
        else
            Ok(views.html.ownerCheckEvent(EventData.ownerForm,UserNameData.userNameForm))
    }

    def userTicketHistory() = Action { implicit request: Request[AnyContent] =>
        val formData : UserNameData = UserNameData.userNameForm.bindFromRequest().get

        val userResult: Future[Seq[User]] = (userActor ? FindByUserName(formData.username)).mapTo[Seq[User]]
        val userList = Await.result(userResult, 5 seconds)
        if (!userList.isEmpty)
            Ok(views.html.userTicketHistory(userList.head))
        else
            Ok(views.html.ownerCheckEvent(EventData.ownerForm ,UserNameData.userNameForm))
    }

}