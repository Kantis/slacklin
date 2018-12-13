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

class ApiTest : MockedEndpointBase() {

    private val url = "${Endpoint.baseUrl}api.test"

    init {
        val errorJson = {}::class.java.getResource("/endpoint/api/error.json").readText()
        val okJson = {}::class.java.getResource("/endpoint/api/ok.json").readText()

        every {
            http.post(
                url,
                match { it.containsKey("error") })
        } answers { errorJson }

        every {
            http.post(
                url,
                match { !it.containsKey("error") }
            )
        } answers { okJson }
    }

    @Test
    fun `args are passed`() {
        runBlocking {
            val token = "some-token"
            val client = SlackWebClientImpl(token, http)
            val args = mapOf("hej" to "hello", "x" to "z")

            val response = client.apiTest(argsToReturn = args)

            with(response) {
                assertTrue(ok)

                verify { http.post(url, mapOf("token" to token) + args) }
            }
        }
    }

    @Test
    fun `error is passed`() {
        runBlocking {
            val token = "some-token"
            val client = SlackWebClientImpl(token, http)

            val error = "some-error"
            val response = client.apiTest(error = error)

            assertFalse(response.ok)

            verify { http.post(url, mapOf("token" to token, "error" to error)) }
        }
    }

    @Test
    fun `token is added to data`() {
        val token = "xoxp123"
        val client = SlackWebClientImpl(token, http)

        runBlocking {
            client.apiTest()

            verify(exactly = 1) { http.post(url, mapOf("token" to token)) }
        }
    }

}