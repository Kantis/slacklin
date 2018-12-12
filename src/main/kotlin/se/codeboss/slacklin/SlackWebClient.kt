package se.codeboss.slacklin

import se.codeboss.slacklin.model.*

interface SlackWebClient {
    suspend fun apiTest(error: String? = null, argsToReturn: Map<String, String> = mapOf()): ApiTestResponse
    suspend fun authTest(): AuthTestResponse

    suspend fun conversationsList(
        limit: Int = 100,
        cursor: String? = null,
        excludeArchived: Boolean = true,
        types: List<ChannelType> = listOf(ChannelType.PublicChannel)
    ): ConversationsListResponse

    suspend fun conversationsInfo(
        channel: ChannelId
    ): ConversationsInfoResponse

    suspend fun usersList(limit: Int = 0, cursor: String? = null): UsersListResponse
}
