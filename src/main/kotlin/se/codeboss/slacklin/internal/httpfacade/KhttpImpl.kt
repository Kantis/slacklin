package se.codeboss.slacklin.internal.httpfacade

class KhttpImpl : Http {
    override fun get(url: String, data: Map<String, String>?): HttpResponse {
        if (data != null)
            return KhttpResponseImpl(khttp.get(url, data = data))

        return KhttpResponseImpl(khttp.get(url))
    }

    override fun post(url: String, data: Map<String, String>?): HttpResponse {
        if (data != null)
            return KhttpResponseImpl(khttp.post(url, data = data))

        return KhttpResponseImpl(khttp.post(url))
    }

}