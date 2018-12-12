package se.codeboss.slackclient

import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class SlackWebClientImplTest {

    @Test
    fun `burst apiTest`() {

        val timestamp = System.currentTimeMillis()

        runBlocking {
            val client = SlackWebClientImpl("dummy")

            client.apiTest()
            client.apiTest()
            client.apiTest()
        }

        val delta = System.currentTimeMillis() - timestamp

        assertTrue(delta > 2000, "Delta was $delta")

    }

    @Test
    fun `args are passed`() {
        runBlocking {
            val client = SlackWebClientImpl("dummy")

            val argsToReturn = mapOf("hej" to "hello", "x" to "z")

            val response = client.apiTest(argsToReturn = argsToReturn)
            assertTrue(response.ok)
            assertEquals(argsToReturn, response.args)
        }
    }

    @Test
    fun `error is passed`() {
        runBlocking {
            val client = SlackWebClientImpl("dummy")

            val expected = "myError"
            val response = client.apiTest(error = expected)
            assertFalse(response.ok)
            assertEquals(expected, response.error)
        }
    }

}