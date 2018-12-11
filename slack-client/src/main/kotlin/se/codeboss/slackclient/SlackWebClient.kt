package se.codeboss.slackclient

import se.codeboss.slackclient.model.ApiTestResponse
import java.util.*

interface SlackWebClient {
    suspend fun apiTest(error: Optional<String>, argsToReturn: Map<String, String>): ApiTestResponse
}
