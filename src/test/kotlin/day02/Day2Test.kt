package day02

import io.kotlintest.shouldBe
import org.junit.Test

class Day2Test {
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
}