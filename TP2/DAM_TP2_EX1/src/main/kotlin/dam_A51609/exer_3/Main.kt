package org.example.dam_A51609.exer_3

fun main(){
    val logs = listOf(
        " INFO: server started ",
        " ERROR: disk full ",
        " DEBUG: checking config ",
        " ERROR: out of memory ",
        " INFO: request received ",
        " ERROR: connection timeout "
    )

    val pipeline = buildPipeline {
        addStage("Trim") { lines ->
            lines.map { it.trim() }
        }
        addStage("Filter errors") { lines ->
            lines.filter { it.contains("ERROR") }
        }
        addStage("Uppercase") { lines ->
            lines.map { it.uppercase() }
        }
        addStage("Add index") { lines ->
            lines.mapIndexed { index, line -> "${index + 1}. $line" }
        }
    }

    pipeline.describe()
    println("\nResult:")
    pipeline.execute(logs).forEach { println(it) }
}