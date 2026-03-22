package dam_A51609

import dam_A51609.Event.Purchase



sealed class Event(val username: String, val timestamp: Long) {

    class Login(username: String, timestamp: Long) : Event(username, timestamp) {
        override fun toString(): String {
            return "Login(username=$username, timestamp=$timestamp)"
        }
    }

    class Logout(username: String, timestamp: Long) : Event(username, timestamp) {
        override fun toString(): String {
            return "Logout(username=$username, timestamp=$timestamp)"
        }
    }

    class Purchase(username: String, val amount: Double, timestamp: Long) : Event(username, timestamp) {
        override fun toString(): String {
            return "Purchase(username=$username, amount=$amount, timestamp=$timestamp)"
        }
    }



}

fun List<Event>.filterByUser(username: String): List<Event> {



    return this.filter { it.username == username }
}


fun List<Event>.totalSpent (username: String): Double{



   return this.filterIsInstance<Purchase>().filter { it.username == username }.sumOf { it.amount }


}

fun processEvents(events: List<Event>, handler: (Event) -> Unit){

    for (event in events) {
        handler(event)
    }
}

