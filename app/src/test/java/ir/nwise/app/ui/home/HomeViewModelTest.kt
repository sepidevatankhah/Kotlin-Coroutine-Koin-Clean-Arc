package ir.nwise.app.ui.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import ir.nwise.app.domain.usecase.DeleteAlbumUseCase
import ir.nwise.app.domain.usecase.GetAlbumsFromCacheUseCase
import ir.nwise.app.utils.CoroutineTestRule
import org.junit.Before
import org.junit.Rule
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class HomeViewModelTest {

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()
    @get:Rule
    val testCoroutineRule = CoroutineTestRule()

    private lateinit var viewModel: HomeViewModel

    @Mock
    private lateinit var getAlbumsFromCacheUseCase: GetAlbumsFromCacheUseCase

    @Mock
    private lateinit var deleteAlbumUseCase: DeleteAlbumUseCase

    @Before
    fun setUp() {
        viewModel = HomeViewModel(
            getAlbumsFromCacheUseCase = getAlbumsFromCacheUseCase,
            deleteAlbumUseCase = deleteAlbumUseCase
        )
    }
}