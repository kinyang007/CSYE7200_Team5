package actors

import akka.actor._
import services._
import pojos._

object UserActor {
    def props = Props[UserActor]

    case object FindAllUsers
    case class FindByUserName(name: String)
    case class LoginInfo(name: String, password: String)
    case class UpdateUser(user: User)
}

class UserActor extends Actor {
    import UserActor._

    override def receive: Receive = {
        case LoginInfo(name, password) =>
            sender() ! UserService.userLogin(name, password)
        case FindByUserName(name) =>
            sender() ! UserService.findByName(name)
        case UpdateUser(user) =>
            sender() ! UserService.updateUser(user)
        case FindAllUsers =>
            sender() ! UserService.findAll
    }
}
