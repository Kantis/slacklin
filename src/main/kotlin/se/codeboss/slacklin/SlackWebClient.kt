package se.codeboss.slacklin

import se.codeboss.slacklin.model.*
import se.codeboss.slacklin.response.*
import java.time.LocalDateTime

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
        id: ChannelId
    ): ConversationsInfoResponse

    suspend fun conversationsHistory(
        id: ChannelId,
        cursor: String? = null,
        inclusive: Boolean = true,
        latest: LocalDateTime? = null,
        limit: Int? = null,
        oldest: LocalDateTime? = null
    )

    suspend fun usersList(limit: Int = 0, cursor: String? = null): UsersListResponse
}
