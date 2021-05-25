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

    var listState: Parcelable? = null //to save and restore rv's adapter

    fun search(artist: String) {
        val dto = ArtistSearchDto(
            method = MethodType.ARTIST_SEARCH.value,
            artist = artist
        )
        searchArtistUseCase.execute(param = dto) {
            when (this) {
                is UseCaseResult.Success ->
                    this.data.results?.artistMatches?.artists?.let {
                        liveData.postValue(SearchViewState.Loaded(it))
                    }
                is UseCaseResult.Error -> liveData.postValue(SearchViewState.Error(this.exception))
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        searchArtistUseCase.unsubscribe()
    }
}

sealed class SearchViewState {
    object Loading : SearchViewState()
    data class Loaded(val results: List<Artist>) : SearchViewState()
    data class Error(val throwable: Throwable) : SearchViewState()
}