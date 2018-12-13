package se.codeboss.slacklin.internal

import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import se.codeboss.slacklin.model.Channel
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.temporal.ChronoUnit
import kotlin.test.assertEquals

class GsonBuilderTest {


    @Nested
    inner class TypeAdapterTests {

        val gson = GsonBuilder.build()

        @Test
        fun `deserialize ChannelId`() {
            val someId = "BG34934"
            val channel = gson.fromJson("{ \"id\": \"$someId\" }", Channel::class.java)

            assertEquals(someId, channel.id.id)
        }

        @Test
        fun `deserialize LocalDateTime`() {
            val now = Instant.now()
            val second = now.epochSecond
            val deserialized = gson.fromJson(second.toString(), LocalDateTime::class.java)

            assertEquals(LocalDateTime.ofInstant(now, ZoneId.systemDefault()).truncatedTo(ChronoUnit.SECONDS), deserialized)
        }

    }

}