package se.codeboss.slacklin

import io.mockk.mockk
import se.codeboss.slacklin.internal.httpfacade.Http

abstract class MockedEndpointBase {

    val http = mockk<Http>()

}