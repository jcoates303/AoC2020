package day06

import common.InputData
import common.printAndMeasureDuration

// ### Shared data ###

val data = InputData.read("2020-Day6.txt")

// ### Main functions ###
fun main() {
    printAndMeasureDuration("Part One",::solvePart1)
    printAndMeasureDuration("Part Two",::solvePart2)
    printAndMeasureDuration("Part Two (Alternate)",::solvePart2Alt)
}

fun solvePart1():Any {
    return findAllAnswersInGroup(data).sumBy { it.size }
}

fun solvePart2():Any {
    return findSameAnswersInGroup(data).sumBy { it.length }
}

fun solvePart2Alt(): Any {
    return data.splitToSequence("\n\n")
        .map { it.split("\n") }
        .map { it.map(String::toSet) }
        .map { answers -> answers.reduce(Set<Char>::intersect).count() }
        .sum()
}

/// ### Sub-functions ###

fun findAllAnswersInGroup(s:String): List<Set<Char>> {
    return s.splitToSequence("\n\n")
        .map { it.toCharArray().toSet().minus('\n') }
        .toList()
}

fun findSameAnswersInGroup(s:String): List<String> {
    return s.splitToSequence("\n\n")
        .map { it.split('\n')
            .map { it.toCharArray().toSet() }
            .reduce{ acc, i -> acc.intersect(i)}.sorted().joinToString("")
        }.toList()
}

