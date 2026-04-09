package org.example.dam_A51609.exer_1

fun main(){

    val events = listOf(
        Event.Login("alice", 1_000),
        Event.Purchase("alice", 49.99, 1_100),
        Event.Purchase("bob", 19.99, 1_200),
        Event.Login("bob", 1_050),
        Event.Purchase("alice", 15.00, 1_300),
        Event.Logout("alice", 1_400),
        Event.Logout("bob", 1_500)
    )

    // 1. Process and print each event
    processEvents(events) { event ->
        when (event) {
            is Event.Login -> {
                println("[LOGIN] ${event.username} logged in at t=${event.timestamp}")
            }
            is Event.Purchase -> {
                println("[PURCHASE] ${event.username} spent $${event.amount} at t=${event.timestamp}")
            }
            is Event.Logout -> {
                println("[LOGOUT] ${event.username} logged out at t=${event.timestamp}")
            }
        }
    }

    // 2. Total spent
    println("Total spent by alice: $${events.totalSpent("alice")}")
    println("Total spent by bob: $${events.totalSpent("bob")}")

    // 3. Filter events
    println("Events for alice:")
    events.filterByUser("alice").forEach{ println(it) }




}