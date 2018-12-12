package se.codeboss.slacklin.model

data class UsersListResponse(
    val ok: Boolean,
    val members: List<User>,
    val cacheTs: Long,
    val responseMetadata: ResponseMetadata?
)