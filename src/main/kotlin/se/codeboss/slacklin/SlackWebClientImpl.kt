package se.codeboss.slacklin

import se.codeboss.slacklin.internal.Endpoint
import se.codeboss.slacklin.internal.toLowerCaseWithUnderscores
import se.codeboss.slacklin.model.*

class SlackWebClientImpl(private val token: String) : SlackWebClient {

    private val apiTest = Endpoint("api.test", 100)
    private val authTest = Endpoint("auth.test", 1000)
    private val usersList = Endpoint("users.list", 20)
    private val conversationsList = Endpoint("conversations.list", 20)
    private val conversationsInfo = Endpoint("conversations.info", 50)

    override suspend fun apiTest(error: String?, argsToReturn: Map<String, String>): ApiTestResponse {
        val payload = if (error != null) mapOf("error" to error) + argsToReturn else argsToReturn
        return apiTest.post(payload, ApiTestResponse::class.java)
    }

    override suspend fun authTest(): AuthTestResponse {
        return authTest.post(mapOf("token" to token), AuthTestResponse::class.java)
    }

    override suspend fun usersList(limit: Int, cursor: String?): UsersListResponse {
        val data = prepareData(limit, cursor)
        return usersList.get(data, UsersListResponse::class.java)
    }

    override suspend fun conversationsList(
        limit: Int,
        cursor: String?,
        excludeArchived: Boolean,
        types: List<ChannelType>
    ): ConversationsListResponse {
        val data = prepareData(limit, cursor)
        data["exclude_archived"] = excludeArchived.toString()
        data["types"] = types.joinToString(separator = ",") { it.name.toLowerCaseWithUnderscores() }

        return conversationsList.get(data, ConversationsListResponse::class.java)
    }

    override suspend fun conversationsInfo(channelId: ChannelId): ConversationsInfoResponse {
        val data = prepareData()
        data["channel"] = channelId.id

        return conversationsInfo.get(data, ConversationsInfoResponse::class.java)
    }

    private fun prepareData(limit: Int? = null, cursor: String? = null): MutableMap<String, String> {
        val data = HashMap<String, String>()
        data["token"] = token
        if (limit != null) data["limit"] = limit.toString()
        if (cursor != null) data["cursor"] = cursor
        return data
    }

}
