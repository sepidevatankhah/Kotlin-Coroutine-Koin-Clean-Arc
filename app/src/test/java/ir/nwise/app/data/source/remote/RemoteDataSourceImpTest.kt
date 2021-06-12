package ir.nwise.app.data.source.remote

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockitokotlin2.any
import ir.nwise.app.networking.ApiService
import ir.nwise.app.utils.MockWebServerBaseTest
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import java.net.HttpURLConnection

@RunWith(MockitoJUnitRunner::class)
class RemoteDataSourceImpTest : MockWebServerBaseTest() {
    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()
    override fun isMockServerEnabled(): Boolean = true

    private lateinit var apiService: ApiService

    private lateinit var remoteDataSourceImp: RemoteDataSourceImp

    @Before
    override fun setUp() {
        configureMockServer()
        apiService = provideTestApiService()
        remoteDataSourceImp = RemoteDataSourceImp(apiService = apiService)
    }

    @Test
    fun `given response ok when fetching results then return a list with elements`() {
        runBlocking {
            mockHttpResponse("json/search_response_one_item.json", HttpURLConnection.HTTP_OK)
            val apiResponse = remoteDataSourceImp.searchArtist(any())

            assertNotNull(apiResponse)
            assertEquals(apiResponse.results?.artistMatches?.artists?.size, 1)
        }
    }

}