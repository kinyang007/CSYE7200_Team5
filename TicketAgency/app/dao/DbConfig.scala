package daos

import com.mongodb.reactivestreams.client.MongoClients

class DbConfig {

    private val uri = "mongodb+srv://kin19960523:ky19960523@cluster0-vyuoj.mongodb.net/test?w=majority"
    private val client = MongoClients.create(uri)
    protected val db = client.getDatabase("TicketAgency")

}

object DbConfig