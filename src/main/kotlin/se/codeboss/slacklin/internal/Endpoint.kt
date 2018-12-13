package se.codeboss.slacklin.internal

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import se.codeboss.slacklin.internal.httpfacade.Http
import java.util.concurrent.Semaphore


class Endpoint private constructor(methodName: String, callsPerMinute: Long, private val http: Http) {

    private val url: String = "https://slack.com/api/$methodName"
    private val millisBetweenCalls = 60000 / callsPerMinute
    private val semaphore = Semaphore(1)
    private val gson = GsonBuilder.build()

    suspend fun <T> post(payload: Map<String, String>, clazz: Class<T>): T {
        throttle()
        return gson.fromJson(http.post(url, data = payload).text, clazz)
    }

    suspend fun <T> get(parameters: Map<String, String>, clazz: Class<T>): T {
        throttle()
        return gson.fromJson(http.get(url, data = parameters).text, clazz)
    }

    private suspend fun throttle() {
        while (!semaphore.tryAcquire()) {
            delay(millisBetweenCalls/10)
        }

        GlobalScope.launch {
            delay(millisBetweenCalls)
            semaphore.release()
        }
    }

    class Builder(private val http: Http) {

        fun build(methodName: String, callsPerMinute: Long): Endpoint {
            return Endpoint(methodName, callsPerMinute, http)
        }

    }

}