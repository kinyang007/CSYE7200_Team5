package pojos


case class User(_id: Int, name: String, password: String, tickets: List[Ticket])
