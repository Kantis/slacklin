package se.codeboss.slacklin.model

data class ConversationsListResponse(
    val ok: Boolean,
    val channels: List<Channel>
)