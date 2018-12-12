package se.codeboss.slacklin

import se.codeboss.slacklin.internal.Endpoint
import se.codeboss.slacklin.model.ApiTestResponse
import se.codeboss.slacklin.model.AuthTestResponse

class SlackWebClientImpl(private val token: String) : SlackWebClient {
    private val apiTest = Endpoint("api.test", 100)
    private val authTest = Endpoint("auth.test", 1000)

    override suspend fun apiTest(error: String?, argsToReturn: Map<String, String>): ApiTestResponse {
        val payload = if (error != null) mapOf("error" to error) + argsToReturn else argsToReturn
        return apiTest.post(payload, ApiTestResponse::class.java)
    }

    override suspend fun authTest(): AuthTestResponse {
        return authTest.post(mapOf("token" to token), AuthTestResponse::class.java)
    }

}