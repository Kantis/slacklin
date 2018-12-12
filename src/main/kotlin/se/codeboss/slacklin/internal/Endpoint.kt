package se.codeboss.slacklin.internal

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.concurrent.Semaphore


class Endpoint(
    methodName: String,
    callsPerMinute: Long
) {

    private val url = "https://slack.com/api/$methodName"
    private val semaphore = Semaphore(1)
    private val millisBetweenCalls = 60000 / callsPerMinute

    private val gson = GsonBuilder.build()

    suspend fun <T> post(payload: Map<String, String>, clazz: Class<T>): T {
        throttle()
        return gson.fromJson(khttp.post(url, data = payload).text, clazz)
    }

    suspend fun <T> get(parameters: Map<String, String>, clazz: Class<T>): T {
        throttle()
        return gson.fromJson(khttp.get(url, data = parameters).text, clazz)
    }

    private suspend fun throttle() {
        while (!semaphore.tryAcquire()) {
            delay(millisBetweenCalls)
        }

        GlobalScope.launch {
            delay(1000)
            semaphore.release()
        }
    }

}