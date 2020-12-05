package day01

import common.InputData
import common.printAndMeasureDuration

const val target = 2020
val data = InputData.readLines("2020-Day1.txt")

fun findTripletThatMatches(candidates: List<Int>, target:Int):String {
    val s = StringBuffer()
    candidates.forEachIndexed { idx, value ->
        val filter1 = candidates.subList(idx, candidates.lastIndex)
        filter1.forEachIndexed { idx2, value2 ->
            val result = filter1.subList(idx2, filter1.lastIndex).filter { it + value + value2 == target }
            if (!result.isEmpty()) result.forEach {
                s.append("$value + $value2 + $it = $target, $value * $value2 * $it = ${value * value2 * it}")
            }
        }
    }
    return "$s"
}

fun solvePart1(): String {
    return findTripletThatMatches(data.map{it.toInt()}.toSortedSet().toList(), target)
}

fun main() {
    printAndMeasureDuration("Part One",::solvePart1)
}