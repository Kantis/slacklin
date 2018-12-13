package se.codeboss.slacklin.response

/*
{
    "ok": true,
    "url": "https://subarachnoid.slack.com/",
    "team": "Subarachnoid Workspace",
    "user": "grace",
    "team_id": "T12345678",
    "user_id": "W12345678"
}
 */
data class AuthTestResponse(
    val ok: Boolean,
    val url: String,
    val team: String,
    val user: String,
    val teamId: String,
    val userId: String
)

