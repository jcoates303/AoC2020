package day05

import common.InputData
import io.kotlintest.shouldBe
import org.junit.Test

class Day5Test {

    private var reservations = readReservations(InputData.readLines("2020-Day5-Test.txt"))

    @Test
    fun `first seat is correct`() {
        reservations[0].row shouldBe 44
        reservations[0].col shouldBe 5
        reservations[0].seatID shouldBe 357
    }

    @Test
    fun `second seat is correct`() {
        reservations[1].row shouldBe 70
        reservations[1].col shouldBe 7
        reservations[1].seatID shouldBe 567
    }

    @Test
    fun `third seat is correct`() {
        reservations[2].row shouldBe 14
        reservations[2].col shouldBe 7
        reservations[2].seatID shouldBe 119
    }

    @Test
    fun `fourth seat is correct`() {
        reservations[3].row shouldBe 102
        reservations[3].col shouldBe 4
        reservations[3].seatID shouldBe 820
    }
}