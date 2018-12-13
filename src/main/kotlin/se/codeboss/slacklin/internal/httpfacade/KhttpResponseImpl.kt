package se.codeboss.slacklin.internal.httpfacade

import khttp.responses.Response

class KhttpResponseImpl(khttpResponse: Response) : HttpResponse {
    override val text: String = khttpResponse.text
}
