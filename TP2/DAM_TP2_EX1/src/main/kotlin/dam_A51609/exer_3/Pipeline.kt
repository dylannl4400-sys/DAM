package org.example.dam_A51609.exer_3

class Pipeline {


    private val stages = mutableListOf<Pair<String, (List<String>) -> List<String>>>()

    fun addStage(name: String, transform: (List<String>) -> List<String>) {
        stages.add(Pair(name, transform))
    }

    fun execute(input: List<String>): List<String> {
        return stages.fold(input) { current, (_, transform) -> transform(current) }
    }

    fun describe() {
        println("Pipeline stages:")
        stages.forEachIndexed { index, (name, _) ->
            println("${index + 1}. $name")
        }
    }

    
    fun compose(firstName: String, secondName: String) {
        val first = stages.find { it.first == firstName }?.second
            ?: throw IllegalArgumentException("Stage '$firstName' not found")
        val second = stages.find { it.first == secondName }?.second
            ?: throw IllegalArgumentException("Stage '$secondName' not found")

        stages.removeAll { it.first == firstName || it.first == secondName }

        val composed: (List<String>) -> List<String> = { input -> second(first(input)) }
        addStage("$firstName + $secondName", composed)
    }


    fun fork(other: Pipeline, input: List<String>): Pair<List<String>, List<String>> {
        return Pair(this.execute(input), other.execute(input))
    }
}

fun buildPipeline(init: Pipeline.() -> Unit): Pipeline {
    val pipeline = Pipeline()
    pipeline.init()
    return pipeline
}


