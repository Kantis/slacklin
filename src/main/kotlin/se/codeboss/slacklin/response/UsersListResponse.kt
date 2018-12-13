package se.codeboss.slacklin.response

import se.codeboss.slacklin.model.ResponseMetadata
import se.codeboss.slacklin.model.User
import java.time.LocalDateTime

data class UsersListResponse(
    val ok: Boolean,
    val members: List<User>,
    val cacheTs: LocalDateTime,
    val responseMetadata: ResponseMetadata?
)