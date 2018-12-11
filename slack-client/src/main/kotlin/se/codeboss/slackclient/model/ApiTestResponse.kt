package se.codeboss.slackclient.model

class ApiTestResponse(
    val ok: Boolean,
    val error: String,
    val args: Map<String, String>
)
