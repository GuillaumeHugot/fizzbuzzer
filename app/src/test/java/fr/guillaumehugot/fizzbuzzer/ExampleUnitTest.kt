package fr.guillaumehugot.fizzbuzzer

import fr.guillaumehugot.fizzbuzzer.ui.main.FizzBuzzer
import fr.guillaumehugot.fizzbuzzer.ui.main.toFizzBuzzWord
import org.junit.Assert.*
import org.junit.Test
import java.lang.IllegalArgumentException

class FizzBuzzUnitTest {

    @Test
    fun simpleTest() {
        val result =
            (1..16).map { it.toFizzBuzzWord(listOf(FizzBuzzer(3, "fizz"), FizzBuzzer(5, "buzz"))) }
        val expected = listOf(
            "1",
            "2",
            "fizz",
            "4",
            "buzz",
            "fizz",
            "7",
            "8",
            "fizz",
            "buzz",
            "11",
            "fizz",
            "13",
            "14",
            "fizzbuzz",
            "16"
        )
        assert(result.size == expected.size) {
            "result size doesn't match. Expected: ${expected.size}, got : ${result.size}"
        }
        assert(expected.mapIndexed { index, expectedWord -> result[index] == expectedWord }
            .all { it }) {
            "result doesn't match. Expected: $expected, got : $result"
        }
    }

    @Test
    fun noOrderTest() {
        val result =
            (1..16).map { it.toFizzBuzzWord(listOf(FizzBuzzer(5, "buzz"), FizzBuzzer(3, "fizz"))) }
        val expected = listOf(
            "1",
            "2",
            "fizz",
            "4",
            "buzz",
            "fizz",
            "7",
            "8",
            "fizz",
            "buzz",
            "11",
            "fizz",
            "13",
            "14",
            "fizzbuzz",
            "16"
        )
        assert(result.size == expected.size) {
            "result size doesn't match. Expected: ${expected.size}, got : ${result.size}"
        }
        assert(expected.mapIndexed { index, expectedWord -> result[index] == expectedWord }
            .all { it }) {
            "result doesn't match. Expected: $expected, got : $result"
        }
    }

    @Test
    fun repetitionTest() {
        val result =
            (1..6).map { it.toFizzBuzzWord(listOf(FizzBuzzer(2, "fizz"), FizzBuzzer(2, "buzz"))) }
        val expected = listOf(
            "1",
            "fizzbuzz",
            "3",
            "fizzbuzz",
            "5",
            "fizzbuzz"
        )
        assert(result.size == expected.size) {
            "result size doesn't match. Expected: ${expected.size}, got : ${result.size}"
        }
        assert(expected.mapIndexed { index, expectedWord -> result[index] == expectedWord }
            .all { it }) {
            "result doesn't match. Expected: $expected, got : $result"
        }
    }

    @Test
    fun outOfLimit() {
        try {
            val result = 1.toFizzBuzzWord(listOf(FizzBuzzer(100, "Test")))
            assert(result == "1") {
                "out of limit failed on value"
            }
        } catch (e: Exception) {
            fail("out of limit failed on exception : $e")
        }
    }

    @Test
    fun emptyListTest() {
        try {
            val result = 1.toFizzBuzzWord(emptyList())
            assert(result == "1") {
                "empty list failed on value. Expected : 1, got : $result"
            }
        } catch (e: Exception) {
            fail("empty list test failed on exception : $e")
        }
    }

    @Test
    fun negativeTest() {
        try {
            val result = 1.toFizzBuzzWord(listOf(FizzBuzzer(-10, "Test")))
            assert(result == "1") {
                "negative test failed on value. Expected : 1, got : $result"
            }
        } catch (e: Exception) {
            fail("negative test failed on exception : $e")
        }
    }

    @Test
    fun zeroTest() {
        try {
            val result = 1.toFizzBuzzWord(listOf(FizzBuzzer(0, "Test")))
            //should be defined on specs, in doubt we allow multiple acceptable behaviors
            assert(result == "1" || result == "Test") {
                "negative test failed on value. Expected : \"1\" or \"Test\", got : $result"
            }
        } catch (e: Exception) {
            fail("negative test failed on exception : $e")
        }
    }
}