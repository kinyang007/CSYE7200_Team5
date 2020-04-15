package services

import pojos.User

import scala.collection.mutable

object Duplicate{
  def checkNoDuplicated : Boolean = {
    val users : Seq[User] = UserService.findAll
    val distinctList : mutable.HashSet[String] = mutable.HashSet.empty
    var soldCount = 0
    var continue = true
    for (user <- users if continue){
      if(user.tickets.nonEmpty){
        for(ticket <- user.tickets if continue){
          soldCount+=1
          if(!distinctList.add(ticket.ticket_id)) {
            println("There's a duplicated ticket")
            continue = false
          }
        }
      }
    }
    println("There's no duplicated ticket")
    println("there are "+soldCount+" tickets")
    continue
  }

}
