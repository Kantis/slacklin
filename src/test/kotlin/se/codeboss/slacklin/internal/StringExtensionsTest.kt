package se.codeboss.slacklin.internal

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class StringExtensionsTest {

    @Test
    fun `only lower case remains unchanged`() {
        assertEquals("abcdefghijklmnopqrstuvwxyz", "abcdefghijklmnopqrstuvwxyz".toLowerCaseWithUnderscores())
    }

    @Test
    fun `upper cased first letter does not result in a leading underscore`() {
        assertEquals("a", "A".toLowerCaseWithUnderscores())
        assertEquals("abc", "Abc".toLowerCaseWithUnderscores())
    }

    @Test
    fun `upper cased letters are converted to underscore and lower cased letter`() {
        assertEquals("a_b_c", "ABC".toLowerCaseWithUnderscores())
        assertEquals("ab_c", "abC".toLowerCaseWithUnderscores())
        assertEquals("my_value", "MyValue".toLowerCaseWithUnderscores())
    }

    @Test
    fun `with join to string`() {
        val strings = listOf("MyName", "MyAge")
        assertEquals("my_name,my_age", strings.joinToString(separator = ",") { it.toLowerCaseWithUnderscores() })
    }

}