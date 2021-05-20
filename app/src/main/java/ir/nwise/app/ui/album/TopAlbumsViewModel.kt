package ir.nwise.app.ui.album

import ir.nwise.app.domain.entities.TopAlbumsDto
import ir.nwise.app.domain.model.Album
import ir.nwise.app.domain.usecase.GetAlbumCountWithNameUseCase
import ir.nwise.app.domain.usecase.SaveAlbumUseCase
import ir.nwise.app.domain.usecase.TopAlbumsUseCase
import ir.nwise.app.domain.usecase.base.UseCaseResult
import ir.nwise.app.ui.base.BaseViewModel

class TopAlbumsViewModel(
    private val topAlbumsUseCase: TopAlbumsUseCase,
    private val saveAlbumUseCase: SaveAlbumUseCase,
    private val getAlbumCountWithNameUseCase: GetAlbumCountWithNameUseCase
) : BaseViewModel<TopAlbumsViewState>() {

    fun getTopAlbums(artist: String) {
        val dto = TopAlbumsDto(artist = artist.toLowerCase())
        topAlbumsUseCase.execute(dto) {
            when (this) {
                is UseCaseResult.Success -> liveData.postValue(TopAlbumsViewState.Loaded(this?.data?.topAlbums?.albums))
                is UseCaseResult.Error -> liveData.postValue(TopAlbumsViewState.Error(this.exception))
            }
        }
    }

    fun saveAlbum(album: Album) {
        getAlbumCountWithNameUseCase.execute(album.name) {
            when (this) {
                is UseCaseResult.Success -> {
                    if (this.data > 0)
                        liveData.postValue(TopAlbumsViewState.AlbumIsSaved)
                    else {
                        saveAlbumUseCase.execute(album) {
                            when (this) {
                                is UseCaseResult.Success -> liveData.postValue(TopAlbumsViewState.SavedAlbum)
                                is UseCaseResult.Error -> liveData.postValue(
                                    TopAlbumsViewState.Error(
                                        this.exception
                                    )
                                )
                            }
                        }
                    }
                }
                is UseCaseResult.Error -> liveData.postValue(TopAlbumsViewState.Error(this.exception))
            }
        }
    }


    override fun onCleared() {
        super.onCleared()
        topAlbumsUseCase.unsubscribe()
        saveAlbumUseCase.unsubscribe()
        getAlbumCountWithNameUseCase.unsubscribe()
    }
}

sealed class TopAlbumsViewState {
    object Loading : TopAlbumsViewState()
    object SavedAlbum : TopAlbumsViewState()
    object AlbumIsSaved : TopAlbumsViewState()
    data class Loaded(val results: List<Album>?) : TopAlbumsViewState()
    data class Error(val throwable: Throwable) : TopAlbumsViewState()
}