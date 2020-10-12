package com.vfalin.sibext

import org.junit.Test

import org.junit.jupiter.api.DisplayName


@DisplayName("Testing FilterClass")
class FilterClassTest {
    private val filterClass = FilterClass()

    private val inputArray = arrayOf(
        arrayOf("00", "01", "02", "03", "04", "05"),
        arrayOf("10", "11", "12", "13", "14", "15"),
        arrayOf("20", "21", "22", "23", "24", "25"),
        arrayOf("30", "31", "32", "33", "34", "35"),
        arrayOf("40", "41", "42", "43", "44", "45")
    )
    private val indexesToRemoveN1 = listOf(2, 4)
    private val indexesToRemoveN2 = listOf(-1, -4)
    private val indexesToRemoveN3 = listOf(-1, -4, 777, 3, 0)
    private val indexesToRemoveN4 = listOf(0)
    private val indexesToRemoveN5 = emptyList<Int>()

    @DisplayName("Test with even values")
    @Test
    fun testWithEvenValues() {
        filterClass.filterArray(inputArray, indexesToRemoveN1)
            .forEach { println(it.toList()) }
    }

    @DisplayName("Test with odd values")
    @Test
    fun testWithOddValues() {
        filterClass.filterArray(inputArray, indexesToRemoveN2)
            .forEach { println(it.toList()) }
    }

    @DisplayName("Test with mixed values")
    @Test
    fun testWithMixedValues() {
        filterClass.filterArray(inputArray, indexesToRemoveN3)
            .forEach { println(it.toList()) }
    }

    @DisplayName("Test with only zero element value")
    @Test
    fun testWithOnlyZeroElementValue() {
        filterClass.filterArray(inputArray, indexesToRemoveN4)
            .forEach { println(it.toList()) }
    }

    @DisplayName("Test with empty array")
    @Test
    fun testWithEmptyArray() {
        filterClass.filterArray(inputArray, indexesToRemoveN5)
            .forEach { println(it.toList()) }
    }
}