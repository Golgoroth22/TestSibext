package com.vfalin.sibext

import org.junit.Test
import org.junit.jupiter.api.Assertions

class FilterTestWithMixedValuesAndRegularInputArray {
    private val filterClass = FilterClass()
    private val inputArray = arrayOf(
        arrayOf("00", "01", "02", "03", "04", "05"),
        arrayOf("10", "11", "12", "13", "14", "15"),
        arrayOf("20", "21", "22", "23", "24", "25"),
        arrayOf("30", "31", "32", "33", "34", "35"),
        arrayOf("40", "41", "42", "43", "44", "45")
    )
    private val expectedArray = arrayOf(
        arrayOf("11", "12", "14", "15"),
        arrayOf("21", "22", "24", "25"),
        arrayOf("41", "42", "44", "45")
    )
    private val indexesToRemove = listOf(-1, -4, 777, 3, 0)

    @Test
    fun test() {
        val result = filterClass.filterArray(inputArray, indexesToRemove)
        result.forEach { println(it.toList()) }
        Assertions.assertArrayEquals(expectedArray, result)
    }
}