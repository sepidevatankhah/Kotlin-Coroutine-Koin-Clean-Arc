package ir.nwise.app.utils

import ir.nwise.app.data.repository.AppRepositoryTest
import ir.nwise.app.networking.ApiService
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import kotlin.reflect.KClass

abstract class MockWebServerBaseTest {

    private lateinit var mockServer: MockWebServer

    @Before
    open fun setUp() {
        this.configureMockServer()
    }

    @After
    open fun tearDown() {
        this.stopMockServer()
    }

    abstract fun isMockServerEnabled(): Boolean

    open fun configureMockServer() {
        if (isMockServerEnabled()) {
            mockServer = MockWebServer()
            mockServer.start()
        }
    }

    open fun stopMockServer() {
        if (isMockServerEnabled()) {
            mockServer.shutdown()
        }
    }

    open fun mockHttpResponse(fileName: String, responseCode: Int) =
        mockServer.enqueue(MockResponse().setResponseCode(responseCode).setBody(getJson(fileName)))

    open  fun <T : Any> mockHttpResponse(clazz: KClass<T>, path: String, responseCode: Int) =
        mockServer.enqueue(mockResponseFromFile(clazz, "json/search_response_one_item.").setResponseCode(responseCode))

    open fun mockHttpResponse(responseCode: Int) =
        mockServer.enqueue(MockResponse().setResponseCode(responseCode))

    private fun getJson(path: String): String {
        val uri = this.javaClass.classLoader?.getResource(path)
        val file = File(uri?.path)
        return String(file.readBytes())
    }

    fun provideTestApiService(): ApiService {
        return Retrofit.Builder().baseUrl(mockServer.url("/")).addConverterFactory(
            GsonConverterFactory.create())
            .client(OkHttpClient.Builder().build()).build().create(ApiService::class.java)
    }
}