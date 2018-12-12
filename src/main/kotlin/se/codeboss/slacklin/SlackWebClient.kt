package se.codeboss.slacklin

import se.codeboss.slacklin.model.ApiTestResponse
import se.codeboss.slacklin.model.AuthTestResponse

interface SlackWebClient {
    suspend fun apiTest(error: String? = null, argsToReturn: Map<String, String> = mapOf()): ApiTestResponse
    suspend fun authTest(): AuthTestResponse
}
