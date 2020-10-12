package com.vfalin.sibext

import org.junit.Test
import org.junit.jupiter.api.Assertions

class FilterTestWithOddValuesAndNotRegularInputArray {
    private val filterClass = FilterClass()
    private val inputArray = arrayOf(
        arrayOf("00", "01", "02", "03", "04", "05"),
        arrayOf("10"),
        arrayOf("24", "25"),
        arrayOf(),
        arrayOf("40", "41", "42", "43", "45")
    )
    private val expectedArray = arrayOf(
        arrayOf("00", "01", "02", "03", "04", "05"),
        arrayOf("10"),
        arrayOf("24", "25"),
        arrayOf(),
        arrayOf("40", "41", "42", "43", "45")
    )
    private val indexesToRemove = listOf(-1, -4)

    @Test
    fun test() {
        val result = filterClass.filterArray(inputArray, indexesToRemove)
        result.forEach { println(it.toList()) }
        Assertions.assertArrayEquals(expectedArray, result)
    }
}