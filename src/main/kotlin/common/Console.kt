package common

import kotlin.system.measureNanoTime

fun printAndMeasureDuration(title: String, function: () -> Any, times:Int = 1) {
    val nanos = measureNanoTime {
        repeat(times-1){function()}
        print("$title: ${function()}")
    } / times
    val millis = nanos / 1000000
    println(" (took ${nanos}ns / ${millis}ms)")
}