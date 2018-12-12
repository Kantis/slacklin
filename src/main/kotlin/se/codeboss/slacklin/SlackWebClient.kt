package se.codeboss.slacklin

import se.codeboss.slacklin.model.ApiTestResponse
import se.codeboss.slacklin.model.AuthTestResponse
import se.codeboss.slacklin.model.UsersListResponse

interface SlackWebClient {
    suspend fun apiTest(error: String? = null, argsToReturn: Map<String, String> = mapOf()): ApiTestResponse
    suspend fun authTest(): AuthTestResponse
    suspend fun usersList(): UsersListResponse
}
