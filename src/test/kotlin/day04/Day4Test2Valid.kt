package day04

import day02.loadData
import io.kotlintest.shouldBe
import org.junit.Test

class Day4Test2Valid {

    private var data = transformData(loadData("2020-Day4-Test2Valid.txt"))

    @Test
    fun `first record is correct`() {
        isValidPass2(data[0]) shouldBe true
    }

    @Test
    fun `second record is correct`() {
        isValidPass2(data[1]) shouldBe true
    }

    @Test
    fun `third record is correct`() {
        isValidPass2(data[2]) shouldBe true
    }

    @Test
    fun `fourth record is correct`() {
        isValidPass2(data[3]) shouldBe true
    }
}
