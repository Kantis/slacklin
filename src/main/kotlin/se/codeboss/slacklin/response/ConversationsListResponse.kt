package se.codeboss.slacklin.response

import se.codeboss.slacklin.model.Channel

data class ConversationsListResponse(
    val ok: Boolean,
    val channels: List<Channel>
)