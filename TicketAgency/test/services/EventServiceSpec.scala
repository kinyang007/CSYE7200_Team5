package services

import org.scalatest.{FlatSpec, Matchers}
import pojos.Event

class EventServiceSpec extends FlatSpec with Matchers {

    "EventServiceSpec" should "find all events" in {
        val result = EventService.findAll

        result.size shouldBe 3
        result.head.name shouldBe "TD Garden"
        result.head.event_type shouldBe "Ball Game"
        result.head.rest_tickets shouldBe Map(
            "vip" -> 50,
            "floor1" -> 5000,
            "floor2" -> 10000,
            "floor3" -> 15000,
            "balcony" -> 29950
        )
        result.head.tickets.size shouldBe 50000
    }

    it should "find events by name" in {
        val result = EventService.findByName("Boston Symphony Orchestra")

        result.head.name shouldBe "Boston Symphony Orchestra"
        result.head.event_type shouldBe "Concert"
        result.head.rest_tickets shouldBe Map(
            "vip" -> 50,
            "floor1" -> 4000,
            "floor2" -> 6000,
            "balcony" -> 9950
        )
        result.head.tickets.size shouldBe 20000
    }

    it should "find events by type" in {
        val result = EventService.findByType("Exhibition")

        result.head.name shouldBe "Museum of Fine Arts"
        result.head.event_type shouldBe "Exhibition"
        result.head.rest_tickets shouldBe Map(
            "vip" -> 50,
            "standard" -> 950
        )
        result.head.tickets.size shouldBe 1000
    }

    it should "update an event" in {
        val event = EventService.findByType("Exhibition").head
        val testEvent = Event(event._id, "test", "", Map(), List())
        EventService.updateEvent(testEvent)
        val newEvent = EventService.findByType("").head
        newEvent shouldBe testEvent
        // restore
        EventService.updateEvent(event)
        val result = EventService.findByType("Exhibition").head
        result shouldBe event
    }
}
