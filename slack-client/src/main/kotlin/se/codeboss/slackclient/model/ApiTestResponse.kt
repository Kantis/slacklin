package se.codeboss.slackclient.model

data class ApiTestResponse(
    val ok: Boolean,
    val error: String,
    val args: Map<String, String>
)
