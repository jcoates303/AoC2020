package day02

import common.InputData
import java.lang.IllegalStateException
val parser = "(\\d+)-(\\d+) (\\w): (\\w+)".toRegex()

fun validatePwd(exp: String):Boolean {
    parser.findAll(exp).first() { m ->
       var o1 = m.groupValues[1].toInt()
       val o2 = m.groupValues[2].toInt()
        val c = m.groupValues[3]
       val s = m.groupValues[4]
       return s.count { c.contains(it) } in o1..o2
    }
    return false
}

fun validatePwd2(exp: String):Boolean {
    parser.findAll(exp).first() { m ->
        var o1 = m.groupValues[1].toInt()
        val o2 = m.groupValues[2].toInt()
        val c = m.groupValues[3][0]
        val s = m.groupValues[4]
        if(o1 < o2 && o2 <= s.length) { // Sanity check
            val c1 = s[o1-1]
            val c2 = s[o2-1]
            return c.equals(c1).xor(c.equals(c2))
        }
        return false
    }
    return false
}

fun main() {
    try{
        var values = InputData.readLines("2020-Day2.txt")
        println( values.count { validatePwd(it) } )
        println( values.count { validatePwd2(it) } )
    } catch(ise: IllegalStateException) {
        println(ise.message)
    }
}