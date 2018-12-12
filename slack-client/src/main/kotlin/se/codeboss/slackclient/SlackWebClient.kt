package se.codeboss.slackclient

import se.codeboss.slackclient.model.ApiTestResponse

interface SlackWebClient {
    suspend fun apiTest(error: String? = null, argsToReturn: Map<String, String> = mapOf()): ApiTestResponse
}
