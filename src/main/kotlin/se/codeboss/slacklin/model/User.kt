package se.codeboss.slacklin.model

data class User (
    val id: String,
    val teamId: String,
    val name: String,
    val deleted: Boolean,
    val color: String,
    val realName: String,
    val tz: String,
    val tzLabel: String,
    val tzOffset: Long,
    val profile: UserProfile,
    val isAdmin: Boolean,
    val isOwner: Boolean,
    val isPrimaryOwner: Boolean,
    val isRestricted: Boolean,
    val isUltraRestricted: Boolean,
    val isBot: Boolean,
    val updated: Long,
    val has2fa: Boolean
)