package dam.exer_2

fun main() {


    println("Welcome to the calculator app")
    println("What kind of operation would you like to do? (Chose the Number)")
    println("1-Addition")
    println("2-Subtraction")
    println("3-Multiplication")
    println("4-Division")
    println("5-AND (&&)")
    println("6-OR (||)")
    println("7-NOT (!)")
    println("8-Left Shift")
    println("9-Right Shift")
    println("10-End Program")

    var choice = readln()


    while (choice != "10") {
        when (choice) {
            "1" -> {
                try {
                    println("You chose Addition")
                    println("Choose two numbers ")
                    println("Number 1: ")
                    val n1 = readln().toFloat()
                    println("Number 2: ")
                    val n2 = readln().toFloat()
                    val n3d = n1 + n2
                    val n3h = n3d.toInt().toString(16)
                    val n3b = n3d != 0f
                    println("Addition of $n1 and $n2 is $n3h in hexadecimal, $n3d in decimal and $n3b in Boolean ")
                } catch (e: Exception) {
                    println("Invalid number(s)!")
                }
            }


            "2" -> {
                try {
                    println("You chose Subtraction")
                    println("Choose two numbers ")
                    println("Number 1: ")
                    val n1 = readln().toFloat()
                    println("Number 2: ")
                    val n2 = readln().toFloat()
                    val n3d = n1 - n2
                    val n3h = n3d.toInt().toString(16)
                    val n3b = n3d != 0f
                    println("Subtraction of $n1 and $n2 is $n3h in hexadecimal, $n3d in decimal and $n3b in Boolean ")
                } catch (e: Exception) {
                    println("Invalid number(s)!")
                }
            }

            "3" -> {
                try {
                    println("You chose Multiplication")
                    println("Choose two numbers ")
                    println("Number 1: ")
                    val n1 = readln().toFloat()
                    println("Number 2: ")
                    val n2 = readln().toFloat()
                    val n3d = n1 * n2
                    val n3h = n3d.toInt().toString(16)
                    val n3b = n3d != 0f
                    println("Multiplication of $n1 and $n2 is $n3h in hexadecimal, $n3d in decimal and $n3b in Boolean ")
                } catch (e: Exception) {
                    println("Invalid number(s)!")
                }
            }

            "4" -> {
                try {
                    println("You chose Division")
                    println("Choose two numbers ")
                    println("Number 1: ")
                    val n1 = readln().toFloat()
                    println("Number 2: ")
                    var n2 = readln().toFloat()
                    while (n2 == 0f) {
                        println("Can't divide by 0, choose a different number!")
                        n2 = readln().toFloat()
                    }
                    val n3d = n1 / n2
                    val n3h = n3d.toInt().toString(16)
                    val n3b = n3d != 0f
                    println("Division of $n1 and $n2 is $n3h in hexadecimal, $n3d in decimal and $n3b in Boolean ")
                } catch (e: Exception) {
                    println("Invalid number(s)!")
                }
            }

            "5" -> {
                try {
                    println("You chose AND (&&)")
                    println("Choose two of the following: 'true' or 'false' ")
                    println("First true or false: ")
                    var n1 = readln().lowercase()
                    while (!n1.equals("true") && !n1.equals("false")) {
                        println("choose between 'true' or false")
                        n1 = readln().lowercase()
                    }
                    val n1b = n1.toBoolean()
                    println("Second true or false: ")
                    var n2 = readln().lowercase()
                    while (!n2.equals("true") && !n2.equals("false")) {
                        println("choose between 'true' or false")
                        n2 = readln().lowercase()
                    }
                    val n2b = n2.toBoolean()
                    val n3d = n1b && n2b
                    println("AND (&&) of $n1 and $n2 is $n3d ")
                } catch (e: Exception) {
                    println("Choose between 'true' or 'false'!")
                }
            }

            "6" -> {
                try {
                    println("You chose OR (||)")
                    println("Choose two of the following: 'true' or 'false' ")
                    println("First true or false: ")
                    var n1 = readln().lowercase()
                    while (!n1.equals("true") && !n1.equals("false")) {
                        println("choose between 'true' or false")
                        n1 = readln()
                    }
                    val n1b = n1.toBoolean()
                    println("Second true or false: ")
                    var n2 = readln().lowercase()
                    while (!n2.equals("true") && !n2.equals("false")) {
                        println("choose between 'true' or false")
                        n2 = readln().lowercase()
                    }
                    val n2b = n2.toBoolean()
                    val n3d = n1b || n2b
                    println("OR (||) of $n1 and $n2 is $n3d ")
                } catch (e: Exception) {
                    println("Choose between 'true' or 'false'!")
                }
            }

            "7" -> {
                try {
                    println("You chose NOT (!)")
                    println("Choose one of the following: 'true' or 'false' ")
                    var n1 = readln().lowercase()
                    while (!n1.equals("true") && !n1.equals("false")) {
                        println("choose between 'true' or false")
                        n1 = readln().lowercase()
                    }
                    val n1b = n1.toBoolean()
                    val n3d = !n1b
                    println("NOT (!) of $n1 is $n3d ")
                } catch (e: Exception) {
                    println("Choose between 'true' or 'false'!")
                }
            }

            "8" -> {
                try {
                    println("You chose Left Shift")
                    println("Choose one number to shift and the respective number of left shifts: ")
                    println("Number to shift: ")
                    var n1 = readln().toInt()
                    println("Number of left shifts: ")
                    var n2 = readln().toInt()
                    val n3d = n1 shl n2
                    println("$n1 left shifted $n2 times is $n3d ")
                } catch (e: Exception) {
                    println("Choose a number!")
                }
            }

            "9" -> {
                try {
                    println("You chose Right Shift")
                    println("Choose one number to shift and the respective number of right shifts: ")
                    println("Number to shift: ")
                    var n1 = readln().toInt()
                    println("Number of right shifts: ")
                    var n2 = readln().toInt()
                    val n3d = n1 shr n2
                    println("$n1 right shifted $n2 times is $n3d ")
                } catch (e: Exception) {
                    println("Choose a number!")
                }
            }

            "10" -> println("Ending program!")
            else -> println("Choose a valid number!")
        }
        println("Choose another option:")
        println("1-Addition")
        println("2-Subtraction")
        println("3-Multiplication")
        println("4-Division")
        println("5-AND (&&)")
        println("6-OR (||)")
        println("7-NOT (!)")
        println("8-Left Shift")
        println("9-Right Shift")
        println("10-End Program")
        choice = readln()
    }


}