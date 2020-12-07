package day07

import common.InputData
import common.printAndMeasureDuration

const val shinyGold = "shiny gold"
val data = InputData.readLines("2020-Day7.txt")
                    .map{ it.splitToSequence(" bags contain ").toList() }

data class Bags (val num:Int, val color:String)
operator fun Bags.times(b:Bags):Bags {
    return if(this.color == b.color)
        Bags(this.num * b.num,  this.color)
    else
        this
}

fun main() {
    printAndMeasureDuration("Part One",::solvePart1)
    // printAndMeasureDuration("Part Two",::solvePart2)
}

fun solvePart1():Any {
    val d = data.map(::parseLine)
    return findColoredBags(shinyGold, d).size
}

fun solvePart2():Any {
    return -1
}

/// ### Sub-functions ###

val bagDef = Regex("\\s?(\\d+) (\\w+ \\w+) bags?\\.?")
fun parseLine(bl:List<String>): Pair<String, List<Bags>> {
    if(bl[1] == "no other") return bl[0] to emptyList()
    val content = bl[1]
        .splitToSequence(',')
        .mapNotNull {
            bagDef.matchEntire(it)
                ?.destructured
                ?.let { (num, color) -> Bags(num.toInt(), color) }
        }
        .toList()
    return bl[0] to content
}

fun expand(l: List<Bags>, dict:List<Pair<String,List<Bags>>>):List<Bags> {
    var t = emptyList<Bags>().toMutableList()
    l.forEachIndexed { index, p ->
        dict.firstOrNull { s -> s.first == p.color}?.second?.let { t.addAll(it) }
    }
    return t
}

fun expandList(color: String, data: List<Pair<String, List<Bags>>>, secondPass: List<Pair<String, List<Bags>>>): List<Pair<String, List<Bags>>> {
    val (exclude, secondPass) = data.partition { p -> p.second == emptyList<Bags>() }
    val (stay, expand) = secondPass.partition { p -> p.second.any { it.color == color } }
    val end = expand.map { it.first to expand(it.second, secondPass) }
    val result = stay.toMutableList()
    if(!end.isEmpty())
        result.addAll(expandList(color, end, secondPass))
    return result.toList()
}

fun findColoredBags(color: String, d: List<Pair<String, List<Bags>>>): List<Pair<String, List<Bags>>> {
    val (exclude, secondPass) = d.partition { p -> p.second == emptyList<Bags>() }
    return expandList(color, d, secondPass)
}