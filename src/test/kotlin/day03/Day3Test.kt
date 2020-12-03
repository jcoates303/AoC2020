package day03

import io.kotlintest.shouldBe
import org.junit.Test

class Day3Test {
    private val testData by lazy { loadData("2020-Day3-Test.txt") }
    private val treeMap by lazy { createTreeMap(testData) }
    private val mapSize by lazy { mapSize(testData) }
    private val initCoords = 0 to 0

    @Test
    fun test1() {
        countHitsInPath(initCoords, 1 to 1, mapSize, treeMap) shouldBe 2
    }

    @Test
    fun test2() {
        countHitsInPath(initCoords, 1 to 3, mapSize, treeMap) shouldBe 7
    }

    @Test
    fun test3() {
        countHitsInPath(initCoords, 1 to 5, mapSize, treeMap) shouldBe 3
    }

    @Test
    fun test4() {
        countHitsInPath(initCoords, 1 to 7, mapSize, treeMap) shouldBe 4
    }

    @Test
    fun test5() {
        countHitsInPath(initCoords, 2 to 1, mapSize, treeMap) shouldBe 2
    }

    @Test
    fun `test trees are in the right place`() {
        treeMap.contains(0 to 0) shouldBe false
        treeMap.contains(1 to 0) shouldBe true
        treeMap.contains(1 to 4) shouldBe true
        treeMap.contains(2 to 1) shouldBe true
        treeMap.contains(2 to 0) shouldBe false
    }



}