package actors

import akka.actor._
import services._
import pojos._

object UserActor {
    def props = Props[UserActor]

    case object FindAll
    case class FindByName(name: String)
    case class LoginInfo(name: String, password: String)
    case class UpdateUser(user: User)
}

class UserActor extends Actor {
    import UserActor._

    override def receive: Receive = {
        case LoginInfo(name, password) =>
            sender() ! UserService.userLogin(name, password)
        case FindByName(name) =>
            sender() ! UserService.findByName(name)
        case UpdateUser(user) =>
            sender() ! UserService.updateUser(user)
        case FindAll =>
            sender() ! UserService.findAll
    }
}
