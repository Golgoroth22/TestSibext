package com.vfalin.sibext.utils

class FilterClass {

    fun filterArray(
        inputArray: Array<Array<String>>,
        indexesToRemove: List<Int>
    ): Array<Array<String>> {
        var resultArray = emptyArray<Array<String>>()
        inputArray.forEachIndexed { innerArrayIndex, innerArray ->
            if (!indexesToRemove.contains(innerArrayIndex)) {
                var newInnerArray = emptyArray<String>()
                for (i in innerArray.indices) {
                    if (!indexesToRemove.contains(i)) {
                        newInnerArray += innerArray[i]
                    }
                }
                resultArray += newInnerArray
            }
        }
        return resultArray
    }
}