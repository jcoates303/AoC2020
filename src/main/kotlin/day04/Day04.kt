package day04

import day02.loadData
import kotlinx.serialization.Serializable
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.json.Json

var data = transformData(loadData("2020-Day4.txt"))

fun collapseData(list:List<String>):String {
    val o = emptyList<String>().toMutableList()
    val sb = StringBuffer()
    val sti = list.iterator()
    while(sti.hasNext()) {
        val s = sti.next()
        if(s.isBlank()) {
            sb.append('}')
            o.add(sb.toString())
            sb.delete(0, sb.length) // Reset StringBuffer
        } else {
            if(sb.isNotBlank()) sb.append(',') else sb.append('{')
            sb.append(s.replace(' ',','))
        }
    }
    if(sb.isNotBlank()) {
        sb.append('}')
        o.add(sb.toString())
    }
    return o.joinToString(prefix = "[", postfix = "]", separator = ", ")
}

fun transformData(data:List<String>):List<Passport> {
    return Json{ isLenient = true }.decodeFromString(ListSerializer(Passport.serializer()), collapseData(data))
}

fun isValidPass1(p:Passport):Boolean {
    return p.byr != null && p.iyr !=null && p.eyr != null && p.hgt?.isNotBlank() == true && p.hcl?.isNotBlank() == true && p.ecl?.isNotBlank() == true && p.pid?.isNotBlank() == true
}

fun isValidBirthYear(i:Int?):Boolean {
    return i != null && i in 1920..2002
}

fun isValidIssueYear(i:Int?):Boolean {
    return i != null && i in 2010..2020
}

fun isValidExpirationYear(i:Int?):Boolean {
    return i != null && i in 2020..2030
}

fun isValidHeight(s:String?):Boolean {
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
fun isValidHairColor(s:String?):Boolean {
    if(s.isNullOrBlank()) return false
    return hairColorRegex.matches(s)
}

val validEyeColors = listOf("amb", "blu", "brn", "gry", "grn", "hzl", "oth")
fun isValidEyeColor(s:String?):Boolean {
    if(s.isNullOrBlank()) return false
    return s in validEyeColors
}

val passportIDRegex: Regex by lazy { "[0-9]{9}".toRegex() }
fun isValidPassportID(s:String?):Boolean {
    if(s.isNullOrBlank()) return false
    return passportIDRegex.matches(s)
}

fun isValidPass2(p:Passport):Boolean {
    return isValidBirthYear(p.byr)
            && isValidIssueYear(p.iyr)
            && isValidExpirationYear(p.eyr)
            && isValidHeight(p.hgt)
            && isValidHairColor(p.hcl)
            && isValidEyeColor(p.ecl)
            && isValidPassportID(p.pid)
}

@Serializable
data class Passport(val byr:Int? = null, val iyr:Int? = null, val eyr:Int? = null, val hgt:String? = null, val hcl:String? = null, val ecl:String? = null, val pid:String? = null, val cid:Int? = null)

fun main() {
    println( data.sumBy{ if(isValidPass1(it)) 1 else 0 } )
    println( data.sumBy{ if(isValidPass2(it)) 1 else 0 } )
}