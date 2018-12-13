package se.codeboss.slacklin

import se.codeboss.slacklin.internal.Endpoint
import se.codeboss.slacklin.internal.httpfacade.Http
import se.codeboss.slacklin.internal.toLowerCaseWithUnderscores
import se.codeboss.slacklin.model.ChannelId
import se.codeboss.slacklin.model.ChannelType
import se.codeboss.slacklin.response.*
import java.time.LocalDateTime

class SlackWebClientImpl(private val token: String, http: Http) : SlackWebClient {

    private val endpointBuilder = Endpoint.Builder(http)

    private val apiTest = endpointBuilder.build("api.test", 100)
    private val authTest = endpointBuilder.build("auth.test", 1000)
    private val usersList = endpointBuilder.build("users.list", 20)
    private val conversationsList = endpointBuilder.build("conversations.list", 20)
    private val conversationsInfo = endpointBuilder.build("conversations.info", 50)

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

    override suspend fun conversationsInfo(id: ChannelId): ConversationsInfoResponse {
        val data = prepareData()
        data["channel"] = id.id

        return conversationsInfo.get(data, ConversationsInfoResponse::class.java)
    }

    override suspend fun conversationsHistory(
        id: ChannelId,
        cursor: String?,
        inclusive: Boolean,
        latest: LocalDateTime?,
        limit: Int?,
        oldest: LocalDateTime?
    ) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun prepareData(limit: Int? = null, cursor: String? = null): MutableMap<String, String> {
        val data = HashMap<String, String>()
        data["token"] = token
        if (limit != null) data["limit"] = limit.toString()
        if (cursor != null) data["cursor"] = cursor
        return data
    }

}
