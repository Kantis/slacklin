package se.codeboss.slacklin.internal

import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.JsonDeserializer
import se.codeboss.slacklin.model.ChannelId
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId

object GsonBuilder {
    private val builder =
        Gson().newBuilder()
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .registerTypeAdapter(
                ChannelId::class.java,
                JsonDeserializer { elem, _, _ -> ChannelId(elem.toString().trim { it == '"' })
            })
            .registerTypeAdapter(
                LocalDateTime::class.java,
                JsonDeserializer { elem, _, _ -> LocalDateTime.ofInstant(Instant.ofEpochSecond(elem.asLong), ZoneId.systemDefault())})

    fun build(): Gson {
        return builder.create()
    }
}