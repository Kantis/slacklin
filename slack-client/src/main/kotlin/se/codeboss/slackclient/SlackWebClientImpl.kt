package se.codeboss.slackclient

import com.google.gson.GsonBuilder
import khttp.post
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import se.codeboss.slackclient.model.ApiTestResponse
import java.util.concurrent.Semaphore

class SlackWebClientImpl(token: String) : SlackWebClient {
    val apiTest = Semaphore(1)
    val gson = GsonBuilder().create()

    override suspend fun apiTest(error: String?, argsToReturn: Map<String, String>): ApiTestResponse {
        while (!apiTest.tryAcquire()) {
            delay(100)
        }

        GlobalScope.launch {
            delay(1000)
            apiTest.release()
        }

        val payload = if (error != null) mapOf("error" to error) + argsToReturn else argsToReturn

        return gson.fromJson(
            post("https://slack.com/api/api.test", data = payload).text,
            ApiTestResponse::class.java
        )
    }
}
