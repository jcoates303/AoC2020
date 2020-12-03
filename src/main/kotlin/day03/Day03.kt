package day03

import common.InputData
import java.lang.IllegalStateException

class Day03 {
    fun loadData(path:String):Set<Pair<Int,Int>> {
        var returnSet = emptySet<Pair<Int,Int>>()
        try {
            val input = InputData.readLines(path)
            return returnSet
        } catch(ise: IllegalStateException) {
            println(ise.message)
            return returnSet
        }
    }

}