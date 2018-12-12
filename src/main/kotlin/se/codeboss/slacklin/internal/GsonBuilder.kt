package se.codeboss.slacklin.internal

import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson

object GsonBuilder {
    private val builder =
        Gson().newBuilder()
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)

    fun build(): Gson {
        return builder.create()
    }
}