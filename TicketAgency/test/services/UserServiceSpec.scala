package services

import org.scalatest.{FlatSpec, Matchers}
import pojos._

class UserServiceSpec extends FlatSpec with Matchers {

    "UserService" should "find one user through login info" in {
        val result = UserService.userLogin("user00001", "123456")

        result.head.name shouldBe "user00001"
        result.head.password shouldBe "123456"
    }

    it should "find one user through name" in {
        val result = UserService.findByName("user00001")

        result.head.name shouldBe "user00001"
    }

    it should "find all users" in {
        val result = UserService.findAll

        result.size shouldBe 25000
    }

    it should "update one user" in {
        val user = UserService.findByName("user00001").head
        val testUser = User(user._id, "test", "", List())
        UserService.updateUser(testUser)
        val testResult = UserService.findByName("test").head
        testResult shouldBe testUser
        // restore
        UserService.updateUser(user)
        val result = UserService.findByName(user.name).head
        result shouldBe user
    }
}
