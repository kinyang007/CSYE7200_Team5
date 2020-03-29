package daos

import com.mongodb.reactivestreams.client.FindPublisher
import org.bson.codecs.configuration.CodecRegistries.{fromProviders, fromRegistries}
import org.mongodb.scala.MongoClient.DEFAULT_CODEC_REGISTRY
import org.mongodb.scala.bson.codecs.Macros._
import org.mongodb.scala.model.Filters
import pojos._

object UserDao extends DbConfig {

    private val codecRegistry = fromRegistries(fromProviders(classOf[User]), DEFAULT_CODEC_REGISTRY)
    private val usersCollection = db.getCollection("users", classOf[User])
        .withCodecRegistry(codecRegistry)

    def findAll: FindPublisher[User] = usersCollection.find(classOf[User])

    def findByLogin(name: String, password: String): FindPublisher[User] =
        usersCollection.find(
            Filters.and(
                Filters.eq("name", name),
                Filters.eq("password", password)
            )
        )

}
