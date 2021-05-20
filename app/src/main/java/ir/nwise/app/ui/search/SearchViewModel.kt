package ir.nwise.app.ui.search

import android.os.Parcelable
import ir.nwise.app.domain.entities.ArtistSearchDto
import ir.nwise.app.domain.entities.MethodType
import ir.nwise.app.domain.model.Artist
import ir.nwise.app.domain.usecase.SearchArtistUseCase
import ir.nwise.app.domain.usecase.base.UseCaseResult
import ir.nwise.app.ui.base.BaseViewModel

class SearchViewModel(private val searchArtistUseCase: SearchArtistUseCase) :
    BaseViewModel<SearchViewState>() {

    private var currentPage = 1
    var listState: Parcelable? = null //to save and restore rv's adapter

    fun search(artist: String) {
        val dto = ArtistSearchDto(
            method = MethodType.ARTIST_SEARCH.value,
            artist = artist,
            page = currentPage
        )
        searchArtistUseCase.execute(dto) {
            when (this) {
                is UseCaseResult.Success -> liveData.postValue(SearchViewState.Loaded(this?.data?.results?.artistMatches?.artists))
                is UseCaseResult.Error -> liveData.postValue(SearchViewState.Error(this.exception))
            }
        }
    }

    fun loadDataNextPage(artist: String) {
        currentPage++
        search(artist)
    }

    override fun onCleared() {
        super.onCleared()
        searchArtistUseCase.unsubscribe()
    }
}

sealed class SearchViewState {
    object Loading : SearchViewState()
    data class Loaded(val results: List<Artist>?) : SearchViewState()
    data class Error(val throwable: Throwable) : SearchViewState()
}