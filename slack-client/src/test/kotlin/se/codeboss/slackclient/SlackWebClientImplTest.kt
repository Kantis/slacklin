package se.codeboss.slackclient

import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import java.util.*

class SlackWebClientImplTest {

    @Test
    fun `burst apiTest`() {

        val timestamp = System.currentTimeMillis()

        runBlocking {
            val client = SlackWebClientImpl()

            client.apiTest(Optional.empty(), mapOf())
            client.apiTest(Optional.empty(), mapOf())
            client.apiTest(Optional.empty(), mapOf())
        }

        val delta = System.currentTimeMillis() - timestamp

        assertTrue(delta > 2000, "Delta was $delta")

    }

}