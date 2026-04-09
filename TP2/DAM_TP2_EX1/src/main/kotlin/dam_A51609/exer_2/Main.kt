package org.example.dam_A51609.exer_2

fun main(){

    val freq = Cache<String, Int>()

    freq.put("kotlin", 1)
    freq.put("scala", 1)
    freq.put("haskell", 1)

    println("")
    println("--- Word frequency cache---")
    println("Size: ${freq.size()}")
    println("Frequency of \"kotlin\": ${freq.get("kotlin")}")

    println("getOrPut \"kotlin\": ${freq.getOrPut("kotlin") { 0 }}")
    println("getOrPut \"java\": ${freq.getOrPut("java") { 0 }}")
    println("Size after getOrPut: ${freq.size()}")

    println("Transform \"kotlin\" (+1): ${freq.transform("kotlin") { it + 1 }}")
    println("Transform \"cobol\" (+1): ${freq.transform("cobol") { it + 1 }}")

    println("Snapshot: ${freq.snapshot()}")

    val registry = Cache<Int, String>()

    registry.put(1, "Alice")
    registry.put(2, "Bob")

    println("")
    println("--- Id registry cache---")
    println("Id 1 -> ${registry.get(1)}")
    println("Id 2 -> ${registry.get(2)}")

    registry.evict(1)

    println("After evict id 1, size: ${registry.size()}")
    println("Id 1 after evict -> ${registry.get(1)}")

    println("")
    println("--- Frequency cache with a count greater than zero---")
    println(freq.filterValues { it > 0 })
}