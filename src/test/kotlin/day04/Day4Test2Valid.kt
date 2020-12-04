package day04

import common.InputData
import io.kotlintest.shouldBe
import org.junit.Test

class Day4Test2Valid {

    private val data = InputData.read("2020-Day4-Test2Valid.txt")
    private val transform1 = transformData(data)
    private val transform2 = parsePassports(data)

    @Test
    fun `first record is correct`() {
        isValidPass2(transform1[0]) shouldBe true
    }

    @Test
    fun `second record is correct`() {
        isValidPass2(transform1[1]) shouldBe true
    }

    @Test
    fun `third record is correct`() {
        isValidPass2(transform1[2]) shouldBe true
    }

    @Test
    fun `fourth record is correct`() {
        isValidPass2(transform1[3]) shouldBe true
    }

    @Test
    fun `first alt record is correct`() {
        isValidPass2(transform2[0]) shouldBe true
    }

    @Test
    fun `second alt record is correct`() {
        isValidPass2(transform2[1]) shouldBe true
    }

    @Test
    fun `third alt record is correct`() {
        isValidPass2(transform2[2]) shouldBe true
    }

    @Test
    fun `fourth alt record is correct`() {
        isValidPass2(transform2[3]) shouldBe true
    }
}
