package org.example.dam.exer_3

import kotlin.math.round
import kotlin.math.roundToInt

fun main() {

    println(a().filter { it > 1 }.take(10).toList())
}

fun a(): Sequence<Double> {

    val a = 100
    val i = a.toDouble()
    val seq = generateSequence(i) { (it * 0.6) }.map {(it*100).roundToInt() / 100.00 }
    return seq
}