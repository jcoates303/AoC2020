package day07

import common.InputData
import io.kotlintest.shouldBe
import org.junit.Test

class Day7Test {

    private val data = InputData.readLines("2020-Day7-Test.txt")
                                .map{ it.splitToSequence(" bags contain ").toList() }
                                .map(::parseLine)

    @Test
    fun `Answer count should be correct`() {
        findColoredBags(shinyGold, data).size shouldBe 4
    }
}