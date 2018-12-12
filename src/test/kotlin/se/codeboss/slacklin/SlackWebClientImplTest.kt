package se.codeboss.slacklin

import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import se.codeboss.slacklin.model.ChannelType
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class SlackWebClientImplTest {

    /**
     * ApiTest tests are using LIVE APIs and should not be part of any CI-workflow
     * Future tests should instead mock data and these tests should be marked as
     * @Ignored, intended for manual use
     */
    @Nested
    inner class ApiTestTest {

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

    /**
     * @see ApiTestTest
     */
    @Nested
    inner class AuthTestTest {

        @Test
        fun `bogus token`() {
            runBlocking {
                val client = SlackWebClientImpl("not-a-token")
                val response = client.authTest()

                assertFalse(response.ok)
            }
        }

        @Test
        fun `real token`() {
            runBlocking {
                val client = SlackWebClientImpl(System.getProperty("slack.token"))
                val response = client.authTest()

                with(response) {
                    assertTrue(ok)
                    assertTrue(url.contains("slack.com/"))
                    assertTrue(team.isNotEmpty())
                    assertTrue(user.isNotEmpty())
                    assertTrue(teamId.isNotEmpty())
                    assertTrue(userId.isNotEmpty())
                }
            }

        }

    }

    @Nested
    inner class UsersListTest {

        @Disabled
        @Test
        fun `make a real call`() {

            runBlocking {
                val client = SlackWebClientImpl(System.getProperty("slack.token"))
                val response = client.usersList(limit = 1)

                // Tip: Add a breakpoint below to inspect the response
                with(response) {
                    assertTrue(ok)
                }
            }
        }

    }

    @Nested
    inner class ConversationsListTest {

        @Disabled
        @Test
        fun `make a real call`() {

            runBlocking {
                val client = SlackWebClientImpl(System.getProperty("slack.token"))
                val response = client.conversationsList(limit = 100, types = listOf(ChannelType.PrivateChannel))
                val x = response.channels.filter { it.name == "codemonkeys" }
                val r2 = client.conversationsInfo(x[0].id)

                // Tip: Add a breakpoint below to inspect the response
                with(r2) {
                    assertTrue(ok)
                }
            }

        }

    }

}