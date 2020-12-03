package day02

import org.junit.Test
import io.kotlintest.shouldBe

class Day2Test {

    var data = loadData("2020-Day2.txt")

    @Test
    fun `test first policy with given sample`() {
        validatePwd("1-3 a: abcde") shouldBe true
        validatePwd("1-3 b: cdefg") shouldBe false
        validatePwd("2-9 c: ccccccccc") shouldBe true
    }

    @Test
    fun `test second policy with given sample`() {
        validatePwd2("1-3 a: abcde") shouldBe true
        validatePwd2("1-3 b: cdefg") shouldBe false
        validatePwd2("2-9 c: ccccccccc") shouldBe false
    }

    @Test
    fun `verify solution part one`() {
        solvePart1(data) shouldBe 546
    }

    @Test
    fun `verify solution part two`() {
        solvePart2(data) shouldBe 275
    }
}