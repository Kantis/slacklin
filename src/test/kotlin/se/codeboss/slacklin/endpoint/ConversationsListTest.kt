package se.codeboss.slacklin.endpoint

import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import se.codeboss.slacklin.MockedEndpointBase
import se.codeboss.slacklin.SlackWebClientImpl
import se.codeboss.slacklin.internal.httpfacade.KhttpImpl
import se.codeboss.slacklin.model.ChannelType

class ConversationsListTest : MockedEndpointBase() {

    @Disabled
    @Test
    fun `make a real call`() {

        runBlocking {
            val client = SlackWebClientImpl(
                System.getProperty("slack.token"),
                KhttpImpl()
            )
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