package dam.exer_1

fun main() {


    // alinea a)
    //val a = IntArray(50) { i -> ((i+1) * (i+1)) }
    //a.forEach { println(it) }

    // alinea b)
    val b1 = 1..50
    val b2 = b1.map { it -> it * it }
    print(b2)

    // alinea c)
    //val c = Array(50) { i -> ((i+1) * (i+1)) }
    //c.forEach { println(it) }

}