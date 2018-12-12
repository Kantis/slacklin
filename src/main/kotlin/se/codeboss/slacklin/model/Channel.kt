package se.codeboss.slacklin.model

import com.google.gson.annotations.SerializedName
import java.time.LocalDateTime

data class Channel (
    val id: ChannelId,
    val name: String,
    val created: LocalDateTime,
    val creator: String,
    val isArchived: Boolean,

    /**
     * Note: member count is not populated for private channels
     */
    @SerializedName("num_members")
    val memberCount: Int
)