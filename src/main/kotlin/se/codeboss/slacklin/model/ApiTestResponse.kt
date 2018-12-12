package se.codeboss.slacklin.model

data class ApiTestResponse(
    val ok: Boolean,
    val error: String,
    val args: Map<String, String>
)
