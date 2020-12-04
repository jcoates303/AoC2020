package common

import kotlin.system.measureNanoTime

fun printAndMeasureDuration(title: String, function: () -> Any) {
    val nanos = measureNanoTime {
        print("$title: ${function()}")
    }
    val millis = nanos / 1000000
    println(" (took ${nanos}ns / ${millis}ms)")
}