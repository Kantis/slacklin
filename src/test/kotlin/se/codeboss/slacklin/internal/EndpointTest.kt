package se.codeboss.slacklin.internal

import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test
import se.codeboss.slacklin.assertNearlyEquals
import se.codeboss.slacklin.internal.httpfacade.Http

class EndpointTest {

    private val http = mockk<Http>()

    init {
        every { http.get(any(), any()) } returns ""
    }

    private val builder = Endpoint.Builder(http)

    @Test
    fun `throttling a hundred requests`() {
        // Allow 60 000 calls per minute ( == 100 calls per second)
        val ep = builder.build("someMethod", 6000)
        val start = System.currentTimeMillis()

        runBlocking {
            IntRange(1, 100).forEach { ep.get(mapOf(), Object::class.java) }
        }

        val delta = System.currentTimeMillis() - start

        // Allow some time for overhead
        assertNearlyEquals(1400, delta, 1000)

        verify(exactly = 100) { http.get(any(), any()) }
    }
}