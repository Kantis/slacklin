package se.codeboss.slacklin

import kotlin.test.assertTrue

fun assertNearlyEquals(expected: Long, actual: Long, maximumDelta: Long) {
    val delta = Math.abs(actual - expected)
    assertTrue(delta < maximumDelta, "Delta of maximum $maximumDelta was allowed but was $delta")
}