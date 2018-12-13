package se.codeboss.slacklin.internal.httpfacade

interface Http {
    fun post(url: String, data: Map<String, String>): String
    fun get(url: String, data: Map<String, String>): String
}
