package se.codeboss.slackclient

import se.codeboss.slackclient.model.ApiTestResponse
import se.codeboss.slackclient.model.AuthTestResponse

interface SlackWebClient {
    suspend fun apiTest(error: String? = null, argsToReturn: Map<String, String> = mapOf()): ApiTestResponse
    suspend fun authTest(): AuthTestResponse
}
