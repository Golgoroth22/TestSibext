package com.vfalin.sibext

import com.vfalin.sibext.utils.FilterClass
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class FilterClassTest {
    private val filterClass = FilterClass()
    private val regularInputArray = arrayOf(
        arrayOf("00", "01", "02", "03", "04", "05"),
        arrayOf("10", "11", "12", "13", "14", "15"),
        arrayOf("20", "21", "22", "23", "24", "25"),
        arrayOf("30", "31", "32", "33", "34", "35"),
        arrayOf("40", "41", "42", "43", "44", "45")
    )
    private val notRegularInputArray = arrayOf(
        arrayOf("00", "01", "02", "03", "04", "05"),
        arrayOf("10"),
        arrayOf("24", "25"),
        arrayOf(),
        arrayOf("40", "41", "42", "43", "45")
    )

    @Test
    fun testWithEmptyArrayAndNotRegularInputArray() {
        val expectedArray = arrayOf(
            arrayOf("00", "01", "02", "03", "04", "05"),
            arrayOf("10"),
            arrayOf("24", "25"),
            arrayOf(),
            arrayOf("40", "41", "42", "43", "45")
        )
        val indexesToRemove = listOf<Int>()
        val result = filterClass.filterArray(notRegularInputArray, indexesToRemove)
        result.forEach { println(it.toList()) }
        Assertions.assertArrayEquals(expectedArray, result)
    }

    @Test
    fun testWithEmptyArrayAndRegularInputArray() {
        val expectedArray = arrayOf(
            arrayOf("00", "01", "02", "03", "04", "05"),
            arrayOf("10", "11", "12", "13", "14", "15"),
            arrayOf("20", "21", "22", "23", "24", "25"),
            arrayOf("30", "31", "32", "33", "34", "35"),
            arrayOf("40", "41", "42", "43", "44", "45")
        )
        val indexesToRemove = listOf<Int>()
        val result = filterClass.filterArray(regularInputArray, indexesToRemove)
        result.forEach { println(it.toList()) }
        Assertions.assertArrayEquals(expectedArray, result)
    }

    @Test
    fun testWithEvenValuesAndNotRegularInputArray() {
        val expectedArray = arrayOf(
            arrayOf("00", "01", "03", "05"),
            arrayOf("10"),
            arrayOf()
        )
        val indexesToRemove = listOf(2, 4)
        val result = filterClass.filterArray(notRegularInputArray, indexesToRemove)
        result.forEach { println(it.toList()) }
        Assertions.assertArrayEquals(expectedArray, result)
    }

    @Test
    fun testWithEvenValuesAndRegularInputArray() {
        val expectedArray = arrayOf(
            arrayOf("00", "01", "03", "05"),
            arrayOf("10", "11", "13", "15"),
            arrayOf("30", "31", "33", "35")
        )
        val indexesToRemove = listOf(2, 4)
        val result = filterClass.filterArray(regularInputArray, indexesToRemove)
        result.forEach { println(it.toList()) }
        Assertions.assertArrayEquals(expectedArray, result)
    }

    @Test
    fun testWithMixedValuesAndNotRegularInputArray() {
        val expectedArray = arrayOf(
            arrayOf(),
            arrayOf("25"),
            arrayOf("41", "42", "45")
        )
        val indexesToRemove = listOf(-1, -4, 777, 3, 0)
        val result = filterClass.filterArray(notRegularInputArray, indexesToRemove)
        result.forEach { println(it.toList()) }
        Assertions.assertArrayEquals(expectedArray, result)
    }

    @Test
    fun testWithMixedValuesAndRegularInputArray() {
        val expectedArray = arrayOf(
            arrayOf("11", "12", "14", "15"),
            arrayOf("21", "22", "24", "25"),
            arrayOf("41", "42", "44", "45")
        )
        val indexesToRemove = listOf(-1, -4, 777, 3, 0)
        val result = filterClass.filterArray(regularInputArray, indexesToRemove)
        result.forEach { println(it.toList()) }
        Assertions.assertArrayEquals(expectedArray, result)
    }

    @Test
    fun testWithOddValuesAndNotRegularInputArray() {
        val expectedArray = arrayOf(
            arrayOf("00", "01", "02", "03", "04", "05"),
            arrayOf("10"),
            arrayOf("24", "25"),
            arrayOf(),
            arrayOf("40", "41", "42", "43", "45")
        )
        val indexesToRemove = listOf(-1, -4)
        val result = filterClass.filterArray(notRegularInputArray, indexesToRemove)
        result.forEach { println(it.toList()) }
        Assertions.assertArrayEquals(expectedArray, result)
    }

    @Test
    fun testWithOddValuesAndRegularInputArray() {
        val expectedArray = arrayOf(
            arrayOf("00", "01", "02", "03", "04", "05"),
            arrayOf("10", "11", "12", "13", "14", "15"),
            arrayOf("20", "21", "22", "23", "24", "25"),
            arrayOf("30", "31", "32", "33", "34", "35"),
            arrayOf("40", "41", "42", "43", "44", "45")
        )
        val indexesToRemove = listOf(-1, -4)
        val result = filterClass.filterArray(regularInputArray, indexesToRemove)
        result.forEach { println(it.toList()) }
        Assertions.assertArrayEquals(expectedArray, result)
    }

    @Test
    fun testWithOnlyZeroElementValueAndNotRegularInputArray() {
        val expectedArray = arrayOf(
            arrayOf(),
            arrayOf("25"),
            arrayOf(),
            arrayOf("41", "42", "43", "45")
        )
        val indexesToRemove = listOf(0)
        val result = filterClass.filterArray(notRegularInputArray, indexesToRemove)
        result.forEach { println(it.toList()) }
        Assertions.assertArrayEquals(expectedArray, result)
    }

    @Test
    fun testWithOnlyZeroElementValueAndRegularInputArray() {
        val expectedArray = arrayOf(
            arrayOf("11", "12", "13", "14", "15"),
            arrayOf("21", "22", "23", "24", "25"),
            arrayOf("31", "32", "33", "34", "35"),
            arrayOf("41", "42", "43", "44", "45")
        )
        val indexesToRemove = listOf(0)
        val result = filterClass.filterArray(regularInputArray, indexesToRemove)
        result.forEach { println(it.toList()) }
        Assertions.assertArrayEquals(expectedArray, result)
    }
}