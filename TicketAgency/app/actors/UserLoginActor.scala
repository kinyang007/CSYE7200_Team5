package actors

import akka.actor._

import services._

object UserLoginActor {
    def props = Props[UserLoginActor]

    case class LoginInfo(name: String, password: String)
}

class UserLoginActor extends Actor {
    import UserLoginActor._

    override def receive: Receive = {
        case LoginInfo(name, password) =>
            sender() ! UserService.userLogin(name, password)
    }
}
