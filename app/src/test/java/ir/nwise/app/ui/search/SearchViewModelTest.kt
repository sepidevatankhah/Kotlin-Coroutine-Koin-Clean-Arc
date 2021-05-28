package ir.nwise.app.ui.search

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockitokotlin2.whenever
import ir.nwise.app.data.repository.AppRepository
import ir.nwise.app.domain.entities.ArtistSearchDto
import ir.nwise.app.domain.entities.MethodType
import ir.nwise.app.domain.model.Artist
import ir.nwise.app.domain.model.ArtistMatch
import ir.nwise.app.domain.model.Search
import ir.nwise.app.domain.model.SearchResult
import ir.nwise.app.domain.usecase.SearchArtistUseCase
import ir.nwise.app.utils.CoroutineTestRule
import ir.nwise.app.utils.captureEmittedData
import junit.framework.Assert.assertNotNull
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.TestCoroutineScope
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class SearchViewModelTest {
    private val testCoroutineDispatcher = TestCoroutineDispatcher()

    // Executes each task synchronously using Architecture Components.
    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    // Executes each task synchronously using Architecture Components.
    @get:Rule
    val coroutinesTestRule = CoroutineTestRule()

    private lateinit var testViewModel: SearchViewModel


    private lateinit var searchArtistUseCase: SearchArtistUseCase

    @Mock
    private lateinit var appRepository: AppRepository

    @Before
    fun setUp() {
        searchArtistUseCase = SearchArtistUseCase(
            appRepository = appRepository,
            coroutineScope = TestCoroutineScope(testCoroutineDispatcher),
            dispatchers = coroutinesTestRule.testDispatcherProvider
        )
        testViewModel = SearchViewModel(searchArtistUseCase)
    }


    @Test
    fun `it must emit #Loaded search's list loaded `() =
        coroutinesTestRule.testDispatcher.runBlockingTest {
            //given
            val artist = "cher"
            val dto = ArtistSearchDto(
                method = MethodType.ARTIST_SEARCH.value,
                artist = artist,
            )
            val artistList = listOf(
                Artist(1, "cher", ""),
                Artist(2, "chers", ""),
                Artist(1, "cherss", "")
            )

            val observer = testViewModel.liveData.captureEmittedData()
            whenever(appRepository.searchArtist(dto)).thenAnswer {
                SearchResult(
                    results = Search(
                        artistMatches = ArtistMatch(
                            artists = artistList
                        )
                    )
                )
            }

            //when
            testViewModel.search(artist = artist)

            //Then
            assertNotNull(testViewModel.liveData.value)
            Assert.assertEquals(
                listOf(SearchViewState.Loaded(artistList)),
                observer.invoke()
            )
        }

}


