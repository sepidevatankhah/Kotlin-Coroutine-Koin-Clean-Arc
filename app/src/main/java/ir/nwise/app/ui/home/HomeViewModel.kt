package ir.nwise.app.ui.home

import ir.nwise.app.domain.model.Album
import ir.nwise.app.domain.model.UserResponse
import ir.nwise.app.domain.usecase.DeleteAlbumUseCase
import ir.nwise.app.domain.usecase.GetAlbumsFromCacheUseCase
import ir.nwise.app.domain.usecase.base.UseCaseResult
import ir.nwise.app.ui.base.BaseViewModel

class HomeViewModel(
    private val getAlbumsFromCacheUseCase: GetAlbumsFromCacheUseCase,
    private val deleteAlbumUseCase: DeleteAlbumUseCase
) : BaseViewModel<HomeViewState>() {
    fun getCachedAlbums(isCalledFromOnViewCreated: Boolean = false) {
        if (isCalledFromOnViewCreated) return
        getAlbumsFromCacheUseCase.execute {
            when (this) {
                is UseCaseResult.Success -> liveData.postValue(HomeViewState.Loaded(this.data))
                is UseCaseResult.Error -> liveData.postValue(HomeViewState.Error(this.exception))
            }
        }
    }

    fun deleteAlbum(album: Album) {
        deleteAlbumUseCase.execute(album) {
            when (this) {
                is UseCaseResult.Success -> liveData.postValue(HomeViewState.DeletedAlbum)
                is UseCaseResult.Error -> liveData.postValue(HomeViewState.Error(this.exception))
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        getAlbumsFromCacheUseCase.unsubscribe()
        deleteAlbumUseCase.unsubscribe()
    }
}

sealed class HomeViewState {
    object Loading : HomeViewState()
    object DeletedAlbum : HomeViewState()
    data class PreLoaded(val session: UserResponse) : HomeViewState()
    data class Loaded(val photos: List<Album>) : HomeViewState()
    data class Error(val throwable: Throwable) : HomeViewState()
}