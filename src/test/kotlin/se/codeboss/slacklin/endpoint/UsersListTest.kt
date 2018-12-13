package se.codeboss.slacklin.endpoint

import io.mockk.every
import io.mockk.verify
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import se.codeboss.slacklin.MockedEndpointBase
import se.codeboss.slacklin.SlackWebClientImpl
import se.codeboss.slacklin.internal.Endpoint
import se.codeboss.slacklin.model.User
import se.codeboss.slacklin.model.UserProfile
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId

class UsersListTest : MockedEndpointBase() {

    val url = "${Endpoint.baseUrl}users.list"

    init {
        val okJson = {}::class.java.getResource("/endpoint/usersList/ok.json").readText()

        every { http.get(url, any()) } answers { okJson }
    }

    @Test
    fun `use limit and cursor`() {
        runBlocking {
            val client = SlackWebClientImpl("actual-token", http)

            client.usersList(limit = 1, cursor = "abc")

            verify { http.get(url, mapOf("token" to "actual-token", "limit" to "1", "cursor" to "abc")) }
        }
    }

    @Test
    fun deserialize() {
        runBlocking {
            val client = SlackWebClientImpl("actual-token", http)

            val response = client.usersList()

            with(response) {
                assertTrue(ok)
                assertEquals(2, members.size)

                assertEquals(
                    User(
                        "W012A3CDE",
                        "T012AB3C4",
                        "spengler",
                        false,
                        "9f69e7",
                        "spengler",
                        "America/Los_Angeles",
                        "Pacific Daylight Time",
                        -25200,
                        UserProfile(
                            "ge3b51ca72de",
                            "https://a.slack-edge.com/avatar/e3b51ca72dee4ef87916ae2b9240df50.jpg",
                            "Egon Spengler",
                            "Egon Spengler",
                            "spengler",
                            "spengler",
                            "spengler@ghostbusters.example.com"
                        ),
                        true,
                        false,
                        false,
                        false,
                        false,
                        false,
                        LocalDateTime.ofInstant(Instant.ofEpochSecond(1502138686), ZoneId.systemDefault()),
                        false
                    ),
                    members[0]
                )

                assertEquals("dXNlcjpVMEc5V0ZYTlo=", responseMetadata?.nextCursor)
            }
        }
    }
}