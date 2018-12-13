package se.codeboss.slacklin.endpoint

import io.mockk.every
import io.mockk.verify
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import se.codeboss.slacklin.MockedEndpointBase
import se.codeboss.slacklin.SlackWebClientImpl
import se.codeboss.slacklin.internal.Endpoint
import kotlin.test.assertEquals

class AuthTest : MockedEndpointBase() {

    val url = "${Endpoint.baseUrl}auth.test"

    init {
        val okJson = {}::class.java.getResource("/endpoint/auth/ok.json").readText()
        val errorJson = {}::class.java.getResource("/endpoint/auth/error.json").readText()

        every { http.post(url, mapOf("token" to "not-a-token")) } answers { errorJson }
        every { http.post(url, mapOf("token" to "actual-token")) } answers { okJson }
    }

    @Test
    fun `bogus token`() {
        runBlocking {
            val client = SlackWebClientImpl("not-a-token", http)
            val response = client.authTest()

            assertFalse(response.ok)
        }
    }

    @Test
    fun `real token`() {
        runBlocking {
            val client = SlackWebClientImpl("actual-token", http)
            val response = client.authTest()

            with(response) {
                assertTrue(ok)
                assertEquals("https://subarachnoid.slack.com/", url)
                assertEquals("Subarachnoid Workspace", team)
                assertEquals("grace", user)
                assertEquals("T12345678", teamId)
                assertEquals("W12345678", userId)
            }

            verify { http.post(url, mapOf("token" to "actual-token")) }
        }

    }

}