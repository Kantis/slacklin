package se.codeboss.slackclient

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import se.codeboss.slackclient.model.ApiTestResponse
import java.util.*
import java.util.concurrent.Semaphore

class SlackWebClientImpl : SlackWebClient {
    val apiTest = Semaphore(1)

    override suspend fun apiTest(error: Optional<String>, argsToReturn: Map<String, String>): ApiTestResponse {
        while (!apiTest.tryAcquire()) {
            delay(1000)
        }

        val apiTestResponse = ApiTestResponse(false, "", mapOf())

        GlobalScope.launch {
            delay(1000)
            apiTest.release()
        }

        return apiTestResponse
    }
}
