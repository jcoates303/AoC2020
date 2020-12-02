package day01

import common.InputData
import java.lang.IllegalStateException

const val target = 2020

fun findPairsThatMatch(candidates:List<Int>, target:Int) {
    candidates.forEachIndexed { idx, value ->
        var filter1 = candidates.subList(idx, candidates.lastIndex)
        filter1.forEachIndexed { idx2, value2 ->
            var result = filter1.subList(idx2, filter1.lastIndex).filter { it + value + value2 == target }
            if (!result.isEmpty()) result.forEach {
                var mult = value * value2 * it
                println("$idx/$idx2: $value + $value2 + $it = $target, $value * $value2 * $it = $mult")
            }
        }
    }
}

fun main() {
    try{
        var values = InputData.readLines("2020-Day1.txt").map{it.toInt()}
        findPairsThatMatch(values, target)
    } catch(ise: IllegalStateException) {
        println(ise.message)
    }
}