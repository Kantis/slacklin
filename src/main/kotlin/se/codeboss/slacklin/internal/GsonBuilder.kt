package se.codeboss.slacklin.internal

import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.JsonDeserializer
import se.codeboss.slacklin.model.ChannelId

object GsonBuilder {
    private val builder =
        Gson().newBuilder()
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .registerTypeAdapter(ChannelId::class.java, JsonDeserializer<ChannelId> { elem, _, _ ->
                ChannelId(elem.toString().trim { it == '"' })
            })

    fun build(): Gson {
        return builder.create()
    }
}