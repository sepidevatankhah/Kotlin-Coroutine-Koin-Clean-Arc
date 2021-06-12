package ir.nwise.app.utils

import okhttp3.mockwebserver.MockResponse
import org.junit.Assert.fail
import java.io.BufferedReader
import java.nio.charset.Charset
import java.util.concurrent.AbstractExecutorService
import java.util.concurrent.Executor
import java.util.concurrent.ExecutorService
import java.util.concurrent.TimeUnit
import kotlin.reflect.KClass

// usable as OkHttpClient dispatcher
fun createImmediateExecutorService(): ExecutorService {
    return object : AbstractExecutorService() {
        override fun shutdown() = Unit
        override fun shutdownNow(): List<Runnable>? = null
        override fun isShutdown(): Boolean = false
        override fun isTerminated(): Boolean = false

        @Throws(InterruptedException::class)
        override fun awaitTermination(l: Long, timeUnit: TimeUnit): Boolean = false
        override fun execute(runnable: Runnable) = runnable.run()
    }
}

// usable as ApolloClient dispatcher
fun createImmediateExecutor(): Executor {
    return Executor { command -> command.run() }
}

// used to read json responses and create mock responses out of it
fun <T : Any> mockResponseFromFile(clazz: KClass<T>, path: String): MockResponse =
    clazz.java.classLoader
        ?.getResourceAsStream(path)
        ?.bufferedReader(Charset.defaultCharset())
        ?.use(BufferedReader::readText)
        ?.run { MockResponse().setBody(this) }
        ?: { throw NullPointerException("Could not load resource file") }()