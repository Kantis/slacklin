package se.codeboss.slacklin

import se.codeboss.slacklin.internal.Endpoint
import se.codeboss.slacklin.model.ApiTestResponse
import se.codeboss.slacklin.model.AuthTestResponse
import se.codeboss.slacklin.model.UsersListResponse

class SlackWebClientImpl(private val token: String) : SlackWebClient {

    private val apiTest = Endpoint("api.test", 100)
    private val authTest = Endpoint("auth.test", 1000)
    private val usersList = Endpoint("users.list", 20)

    override suspend fun apiTest(error: String?, argsToReturn: Map<String, String>): ApiTestResponse {
        val payload = if (error != null) mapOf("error" to error) + argsToReturn else argsToReturn
        return apiTest.post(payload, ApiTestResponse::class.java)
    }

    override suspend fun authTest(): AuthTestResponse {
        return authTest.post(mapOf("token" to token), AuthTestResponse::class.java)
    }

    override suspend fun usersList(limit: Int, cursor: String?): UsersListResponse {
        val data = HashMap<String, String>()
        data["token"] = token
        data["limit"] = limit.toString()
        if (cursor != null) data["cursor"] = cursor

        return usersList.get(data, UsersListResponse::class.java)
    }

}
