package day04

import common.InputData
import io.kotlintest.shouldBe
import org.junit.Test

class Day4Test {

    private val data = InputData.read("2020-Day4-Test.txt")
    private val transform1 = transformData(data)
    private val transform2 = parsePassports(data)

    @Test
    fun `first record is correct`() {
        isValidPass1(transform1[0]) shouldBe true
    }

    @Test
    fun `second record is incorrect`() {
        isValidPass1(transform1[1]) shouldBe false
    }

    @Test
    fun `third record is correct`() {
        isValidPass1(transform1[2]) shouldBe true
    }

    @Test
    fun `fourth record is incorrect`() {
        isValidPass1(transform1[3]) shouldBe false
    }

    @Test
    fun `first alt record is correct`() {
        isValidPass1(transform2[0]) shouldBe true
    }

    @Test
    fun `second alt record is incorrect`() {
        isValidPass1(transform2[1]) shouldBe false
    }

    @Test
    fun `third alt record is correct`() {
        isValidPass1(transform2[2]) shouldBe true
    }

    @Test
    fun `fourth alt record is incorrect`() {
        isValidPass1(transform2[3]) shouldBe false
    }

    @Test
    fun `Birth Year is valid`() {
        isValidBirthYear(2002) shouldBe true
    }

    @Test
    fun `Birth Year is invalid`() {
        isValidBirthYear (2003) shouldBe false
    }

    @Test
    fun `Issue Year is valid`() {
        isValidIssueYear(2020) shouldBe true
    }

    @Test
    fun `Issue Year is invalid`() {
        isValidIssueYear (2021) shouldBe false
    }

    @Test
    fun `Expiration Year is valid`() {
        isValidExpirationYear(2030) shouldBe true
    }

    @Test
    fun `Expiration Year is invalid`() {
        isValidExpirationYear (2031) shouldBe false
    }

    @Test
    fun `Height(in) is valid`() {
        isValidHeight("60in") shouldBe true
    }

    @Test
    fun `Height(cm) is valid`() {
        isValidHeight("190cm") shouldBe true
    }

    @Test
    fun `Height(in) is invalid`() {
        isValidHeight("77in") shouldBe false
    }

    @Test
    fun `Height(cm) is invalid`() {
        isValidHeight("194cm") shouldBe false
    }

    @Test
    fun `Height is invalid`() {
        isValidHeight("194") shouldBe false
    }

    @Test
    fun `Hair color is valid`() {
        isValidHairColor("#123abc") shouldBe true
    }

    @Test
    fun `Hair color is invalid`() {
        isValidHairColor("#123abz") shouldBe false
    }

    @Test
    fun `Hair color is too short`() {
        isValidHairColor("123abc") shouldBe false
    }

    @Test
    fun `Eye color is valid`() {
        isValidEyeColor("brn") shouldBe true
    }

    @Test
    fun `Eye color is invalid`() {
        isValidEyeColor("wat") shouldBe false
    }

    @Test
    fun `Passport ID is valid`() {
        isValidPassportID("000123987") shouldBe true
    }

    @Test
    fun `Passport ID is invalid(character)`() {
        isValidPassportID("abc000123") shouldBe false
    }

    @Test
    fun `Passport ID is invalid(length)`() {
        isValidPassportID("123456") shouldBe false
    }
}
