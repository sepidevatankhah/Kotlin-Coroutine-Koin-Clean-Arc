package ir.nwise.app.data.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.core.app.ApplicationProvider
import com.nhaarman.mockitokotlin2.any
import ir.nwise.app.data.source.database.LocalDataSourceImp
import ir.nwise.app.data.source.remote.RemoteDataSourceImp
import ir.nwise.app.networking.ApiService
import ir.nwise.app.utils.CoroutineTestRule
import ir.nwise.app.utils.MockWebServerBaseTest
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import java.net.HttpURLConnection

@RunWith(MockitoJUnitRunner::class)
//@RunWith(AndroidJUnit4::class)
class AppRepositoryTest : MockWebServerBaseTest() {
    // Executes each task synchronously using Architecture Components.
    @get:Rule
    val coroutinesTestRule = CoroutineTestRule()

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()
    override fun isMockServerEnabled(): Boolean = true

    private lateinit var apiService: ApiService
    private lateinit var appRepository: AppRepositoryImp

    @Mock
    private lateinit var remoteDataSourceImp: RemoteDataSourceImp

    @Mock
    private lateinit var localDataSourceImp: LocalDataSourceImp

    @Before
    override fun setUp() {
        configureMockServer()
        apiService = provideTestApiService()
        appRepository = AppRepositoryImp(
            remoteDataSource = remoteDataSourceImp,
            localDataSource = localDataSourceImp,
            context = ApplicationProvider.getApplicationContext(),
            dispatchers = coroutinesTestRule.testDispatcherProvider
        )
        val y = 0
    }


    @Test
    fun `given response ok when fetching results then return a list with elements`() {
        val y = 0
        runBlocking {
            mockHttpResponse(
                AppRepositoryTest::class,
                "json/search_response_one_item.json",
                HttpURLConnection.HTTP_OK
            )
            val apiResponse = appRepository.searchArtist(any())

            Assert.assertNotNull(apiResponse)
            Assert.assertEquals(apiResponse.results?.artistMatches?.artists?.size, 1)
        }
    }
}