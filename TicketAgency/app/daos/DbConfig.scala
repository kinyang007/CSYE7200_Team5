package daos

import com.mongodb.reactivestreams.client.MongoClients

class DbConfig {

//    private val uri = "mongodb+srv://kin19960523:ky19960523@cluster0-vyuoj.mongodb.net/test?w=majority"
    private val uri = "mongodb://localhost:27017/?readPreference=primary&appname=MongoDB%20Compass%20Community&ssl=false"
    private val client = MongoClients.create(uri)
    protected val db = client.getDatabase("TicketAgency")

}

object DbConfig