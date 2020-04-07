package pojos

case class Event(_id: Int, name: String, event_type: String, rest_tickets: Map[String, Int], tickets: List[Ticket])
