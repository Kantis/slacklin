package se.codeboss.slacklin.model

data class UserProfile (
    val avatarHash: String,
    val image24: String,
    val firstName: String,
    val lastName: String,
    val title: String,
    val phone: String,
    val skype: String,
    val realName: String,
    val realNameNormalized: String,
    val displayName: String,
    val displayNameNormalized: String,
    val email: String
)