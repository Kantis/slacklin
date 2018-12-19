package se.codeboss.slacklin.endpoint

import io.mockk.every
import io.mockk.verify
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test
import se.codeboss.slacklin.MockedEndpointBase
import se.codeboss.slacklin.SlackWebClientImpl
import se.codeboss.slacklin.internal.Endpoint
import se.codeboss.slacklin.model.ChannelType

class ConversationsListTest : MockedEndpointBase() {

    val url = "${Endpoint.baseUrl}conversations.list"

    init {
        val okJson = {}::class.java.getResource("/endpoint/conversationsList/ok.json").readText()

        every { http.get(url, any()) } answers { okJson }
    }

    @Test
    fun `parameters are included`() {
        runBlocking {
            val client = SlackWebClientImpl("actual-token", http)

            client.conversationsList(
                10,
                "some-cursor",
                false,
                listOf(ChannelType.PrivateChannel, ChannelType.InstantMessage)
            )

            verify { http.get(url, mapOf("limit" to "10", "cursor" to "some-cursor", "exclude_archived" to "false", "types" to "private_channel,instant_message", "token" to "actual-token")) }

        }
    }


}