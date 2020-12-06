package day06

import common.InputData
import io.kotlintest.shouldBe
import org.junit.Test

class Day6Test {
    private val data = InputData.read("2020-Day6-Test.txt").splitToSequence("\n\n").toList()

    @Test
    fun `Answer count should be correct`() {
        findAllAnswersInGroup(data).sumBy { it.size } shouldBe 11
    }

    @Test
    fun `Same answer count should be correct`() {
        findSameAnswersInGroup(data).sumBy { it.length } shouldBe 6
    }

}