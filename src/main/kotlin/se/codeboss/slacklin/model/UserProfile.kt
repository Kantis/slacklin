package se.codeboss.slacklin.model

data class UserProfile (
    val avatarHash: String,
    val image_24: String,
    val realName: String,
    val realNameNormalized: String,
    val displayName: String,
    val displayNameNormalized: String,
    val email: String,
    val firstName: String? = null,
    val lastName: String? = null,
    val title: String? = null,
    val phone: String? = null,
    val skype: String? = null
)