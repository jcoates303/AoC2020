package day03

import common.InputData
import java.lang.IllegalStateException

const val tree = '#'
val initialCoordinates = 0 to 0

fun loadData(path: String): List<String> {
    return try {
        InputData.readLines(path)
    } catch (ise: IllegalStateException) {
        println(ise.message)
        emptyList()
    }
}

// Initial map coordinates are at 0:0
fun createTreeMap(input: List<String>): List<Pair<Int, Int>> {
    val trees = emptyList<Pair<Int, Int>>().toMutableList()
    input.forEachIndexed { rowIdx, s ->
        s.forEachIndexed { colIdx, c ->
            if (c == tree) trees.add(rowIdx to colIdx)
        }
    }
    return trees.toList()
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

fun countHitsInPath(init: Pair<Int, Int>, slope: Pair<Int, Int>, mapModulo: Pair<Int, Int>, trees:List<Pair<Int,Int>>): Int {
    var count = 0
    var pos = init - slope
    for(row in 0 until mapModulo.first step slope.first) {
        pos = (pos + slope) % mapModulo
        if(trees.contains(pos)) count++
    }
    return count
}

fun main() {
    val initialData = loadData("2020-Day3.txt")
    val treeMap = createTreeMap(initialData)
    val mapSize = mapSize(initialData)
    val a = countHitsInPath(initialCoordinates, 1 to 1, mapSize, treeMap)
    val b = countHitsInPath(initialCoordinates, 1 to 3, mapSize, treeMap)
    val c = countHitsInPath(initialCoordinates, 1 to 5, mapSize, treeMap)
    val d = countHitsInPath(initialCoordinates, 1 to 7, mapSize, treeMap)
    val e = countHitsInPath(initialCoordinates, 2 to 1, mapSize, treeMap)
    val m = a*b*c*d*e
    println("$a * $b * $c * $d * $e = $m")
}