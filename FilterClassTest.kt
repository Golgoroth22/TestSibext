package com.vfalin.sibext

import org.junit.runner.RunWith
import org.junit.runners.Suite

@RunWith(Suite::class)
@Suite.SuiteClasses(
    FilterTestWithEmptyArrayAndNotRegularInputArray::class,
    FilterTestWithEmptyArrayAndRegularInputArray::class,
    FilterTestWithEvenValuesAndNotRegularInputArray::class,
    FilterTestWithEvenValuesAndRegularInputArray::class,
    FilterTestWithMixedValuesAndNotRegularInputArray::class,
    FilterTestWithMixedValuesAndRegularInputArray::class,
    FilterTestWithOddValuesAndNotRegularInputArray::class,
    FilterTestWithOddValuesAndRegularInputArray::class,
    FilterTestWithOnlyZeroElementValueAndNotRegularInputArray::class,
    FilterTestWithOnlyZeroElementValueAndRegularInputArray::class
)
class FilterClassTest