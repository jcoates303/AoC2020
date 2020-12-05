package day03

import common.InputData
import common.printAndMeasureDuration

const val tree = '#'
val initialCoordinates = 0 to 0

val data = InputData.readLines("2020-Day3.txt")

// Initial map coordinates are at 0:0
fun createTreeMap(input: List<String>): Set<Pair<Int, Int>> {
    val trees = emptySet<Pair<Int, Int>>().toMutableSet()
    input.forEachIndexed { rowIdx, s ->
        s.forEachIndexed { colIdx, c ->
            if (c == tree) trees.add(rowIdx to colIdx)
        }
    }
    return trees
}

fun mapSize(input: List<String>): Pair<Int, Int> {
    val rows = input.size
    val cols = input[0].length
    return rows to cols
}

operator fun Pair<Int, Int>.plus(b: Pair<Int, Int>): Pair<Int, Int> {
    return first + b.first to second + b.second
}

operator fun Pair<Int, Int>.minus(b: Pair<Int, Int>): Pair<Int, Int> {
    return first - b.first to second - b.second
}

operator fun Pair<Int, Int>.rem(b: Pair<Int, Int>): Pair<Int, Int> {
    return first % b.first to second % b.second
}

fun countHitsInPath(init: Pair<Int, Int>, slope: Pair<Int, Int>, mapModulo: Pair<Int, Int>, trees:Set<Pair<Int,Int>>): Int {
    var count = 0
    var pos = init - slope
    for(row in 0 until mapModulo.first step slope.first) {
        pos = (pos + slope) % mapModulo
        if(trees.contains(pos)) count++
    }
    return count
}

fun solvePart2(): String {
    val paths = listOf(1 to 1, 1 to 3, 1 to 5, 1 to 7, 2 to 1).map { countHitsInPath(initialCoordinates, it, mapSize(data), createTreeMap(data)) }
    return "$paths = ${ paths.reduce(Int::times) }"
}

fun main() {
    printAndMeasureDuration("Part Two",::solvePart2)
}