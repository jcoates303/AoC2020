package day04

import common.InputData
import io.kotlintest.shouldBe
import org.junit.Test

class Day4Test2Invalid {

    private val data = InputData.read("2020-Day4-Test2Invalid.txt")
    private val transform1 = transformData(data)
    private val transform2 = parsePassports(data)

    @Test
    fun `first record is incorrect`() {
        isValidPass2(transform1[0]) shouldBe false
    }

    @Test
    fun `second record is incorrect`() {
        isValidPass2(transform1[1]) shouldBe false
    }

    @Test
    fun `third record is incorrect`() {
        isValidPass2(transform1[2]) shouldBe false
    }

    @Test
    fun `fourth record is incorrect`() {
        isValidPass2(transform1[3]) shouldBe false
    }

    @Test
    fun `first alt record is incorrect`() {
        isValidPass2(transform2[0]) shouldBe false
    }

    @Test
    fun `second alt record is incorrect`() {
        isValidPass2(transform2[1]) shouldBe false
    }

    @Test
    fun `third alt record is incorrect`() {
        isValidPass2(transform2[2]) shouldBe false
    }

    @Test
    fun `fourth alt record is incorrect`() {
        isValidPass2(transform2[3]) shouldBe false
    }
}
