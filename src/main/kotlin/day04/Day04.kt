package day04

import common.InputData
import common.printAndMeasureDuration
import kotlinx.serialization.Serializable
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.json.Json

var data = InputData.read("2020-Day4.txt")

fun collapseData(str: String): String {
    return str.replace(' ', ',')
        .splitToSequence("\n\n")
        .mapNotNull { "{ $it }" }
        .joinToString(prefix = "[", postfix = "]", separator = ", ")
}

fun transformData(data: String): List<Passport> {
    return Json { isLenient = true }.decodeFromString(ListSerializer(Passport.serializer()), collapseData(data))
}

@Serializable
data class Passport(
    val byr: Int? = null,
    val iyr: Int? = null,
    val eyr: Int? = null,
    val hgt: String? = null,
    val hcl: String? = null,
    val ecl: String? = null,
    val pid: String? = null,
    val cid: Int? = null
)

data class PassportMap(val map: Map<String, Any?>) {
    private val defaultMap = map.withDefault { null }
    val byr: String? by defaultMap
    val iyr: String? by defaultMap
    val eyr: String? by defaultMap
    val hgt: String? by defaultMap
    val hcl: String? by defaultMap
    val ecl: String? by defaultMap
    val pid: String? by defaultMap
    val cid: String? by defaultMap

    fun toPassport() = Passport(
        this.byr?.toInt(),
        this.iyr?.toInt(),
        this.eyr?.toInt(),
        this.hgt,
        this.hcl,
        this.ecl,
        this.pid,
        this.cid?.toInt()
    )
}

fun parsePassports(data: String): List<Passport> {
    return data
        .split("\n\n")
        .map(::parsePassport)
}

fun parsePassport(unparsedPassport: String): Passport {
    return PassportMap(unparsedPassport
        .trimEnd()
        .replace("\n", " ")
        .split(' ')
        .map {
            val (key, value) = it.split(':')
            key to value
        }.toMap()
    ).toPassport()
}

fun isValidPass1(p: Passport): Boolean {
    return p.byr != null && p.iyr != null && p.eyr != null && p.hgt?.isNotBlank() == true && p.hcl?.isNotBlank() == true && p.ecl?.isNotBlank() == true && p.pid?.isNotBlank() == true
}

fun isValidBirthYear(i: Int?): Boolean {
    return i != null && i in 1920..2002
}

fun isValidIssueYear(i: Int?): Boolean {
    return i != null && i in 2010..2020
}

fun isValidExpirationYear(i: Int?): Boolean {
    return i != null && i in 2020..2030
}

fun isValidHeight(s: String?): Boolean {
    if (s.isNullOrBlank()) return false
    val unitLength = s.takeLast(2)
    if (!(unitLength == "in" || unitLength == "cm")) return false
    val length = s.dropLast(2).toInt()
    return when (unitLength) {
        "in" -> length in 59..76
        "cm" -> length in 150..193
        else -> false
    }
}

val hairColorRegex: Regex by lazy { "#[0-9a-f]{6}".toRegex() }
fun isValidHairColor(s: String?): Boolean {
    if (s.isNullOrBlank()) return false
    return hairColorRegex.matches(s)
}

val validEyeColors = listOf("amb", "blu", "brn", "gry", "grn", "hzl", "oth")
fun isValidEyeColor(s: String?): Boolean {
    if (s.isNullOrBlank()) return false
    return s in validEyeColors
}

val passportIDRegex: Regex by lazy { "[0-9]{9}".toRegex() }
fun isValidPassportID(s: String?): Boolean {
    if (s.isNullOrBlank()) return false
    return passportIDRegex.matches(s)
}

fun isValidPass2(p: Passport): Boolean {
    return isValidBirthYear(p.byr)
            && isValidIssueYear(p.iyr)
            && isValidExpirationYear(p.eyr)
            && isValidHeight(p.hgt)
            && isValidHairColor(p.hcl)
            && isValidEyeColor(p.ecl)
            && isValidPassportID(p.pid)
}

fun solvePart1(): Int {
    return transformData(data).filter { isValidPass1(it) }.count()
}

fun solvePart2(): Int {
    return transformData(data).filter { isValidPass2(it) }.count()
}

fun altSolvePart1(): Int {
    return parsePassports(data).filter { isValidPass1(it) }.count()
}

fun altSolvePart2(): Int {
    return parsePassports(data).filter { isValidPass2(it) }.count()
}

fun main() {
    printAndMeasureDuration("Part One", ::solvePart1)
    printAndMeasureDuration("Part Two", ::solvePart2)
    printAndMeasureDuration("Alt Part One", ::altSolvePart1)
    printAndMeasureDuration("Alt Part Two", ::altSolvePart2)
}