package se.codeboss.slacklin.internal.httpfacade

class KhttpImpl : Http {
    override fun get(url: String, data: Map<String, String>): String {
        return khttp.get(url, data = data).text
    }

    override fun post(url: String, data: Map<String, String>): String {
        return khttp.post(url, data = data).text
    }

}