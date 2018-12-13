package se.codeboss.slacklin.response

data class ApiTestResponse(
    val ok: Boolean,
    val error: String,
    val args: Map<String, String>
)
