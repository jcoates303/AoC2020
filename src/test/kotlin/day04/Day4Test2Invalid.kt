package day04;

import day02.loadData
import io.kotlintest.shouldBe
import org.junit.Test

class Day4Test2Invalid {

    var data = transformData(loadData("2020-Day4-Test2Invalid.txt"))

    @Test
    fun `first record is incorrect`() {
        isValidPass2(data[0]) shouldBe false
    }

    @Test
    fun `second record is incorrect`() {
        isValidPass2(data[1]) shouldBe false
    }

    @Test
    fun `third record is incorrect`() {
        isValidPass2(data[2]) shouldBe false
    }

    @Test
    fun `fourth record is incorrect`() {
        isValidPass2(data[3]) shouldBe false
    }
}
