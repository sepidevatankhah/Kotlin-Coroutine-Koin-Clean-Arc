package ir.nwise.app.domain.usecase

import ir.nwise.app.domain.entities.ArtistSearchDto
import ir.nwise.app.domain.model.SearchResult
import ir.nwise.app.domain.repository.AppRepository
import ir.nwise.app.domain.usecase.base.UseCase

class SearchArtistUseCase(private val appRepository: AppRepository) :
    UseCase<ArtistSearchDto, SearchResult>() {
    override suspend fun executeOnBackground(param: ArtistSearchDto?): SearchResult {
        param?.apply { return appRepository.searchArtist(this) }
        return SearchResult(results = null)
    }
}