package com.vfalin.sibext

import org.junit.Test
import org.junit.jupiter.api.Assertions

class FilterTestWithEvenValuesAndRegularInputArray {
    private val filterClass = FilterClass()
    private val inputArray = arrayOf(
        arrayOf("00", "01", "02", "03", "04", "05"),
        arrayOf("10", "11", "12", "13", "14", "15"),
        arrayOf("20", "21", "22", "23", "24", "25"),
        arrayOf("30", "31", "32", "33", "34", "35"),
        arrayOf("40", "41", "42", "43", "44", "45")
    )
    private val expectedArray = arrayOf(
        arrayOf("00", "01", "03", "05"),
        arrayOf("10", "11", "13", "15"),
        arrayOf("30", "31", "33", "35")
    )
    private val indexesToRemove = listOf(2, 4)

    @Test
    fun test() {
        val result = filterClass.filterArray(inputArray, indexesToRemove)
        result.forEach { println(it.toList()) }
        Assertions.assertArrayEquals(expectedArray, result)
    }
}