package ir.nwise.app.domain.usecase

import ir.nwise.app.domain.entities.ArtistSearchDto
import ir.nwise.app.domain.model.SearchResult
import ir.nwise.app.domain.repository.AppRepository
import ir.nwise.app.domain.usecase.base.DefaultDispatcherProvider
import ir.nwise.app.domain.usecase.base.DispatcherProvider
import ir.nwise.app.domain.usecase.base.UseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope

class SearchArtistUseCase(
    private val appRepository: AppRepository,
    coroutineScope: CoroutineScope = MainScope(),
    dispatchers: DispatcherProvider = DefaultDispatcherProvider()
) : UseCase<ArtistSearchDto, SearchResult>(coroutineScope, dispatchers) {
    override suspend fun executeOnBackground(param: ArtistSearchDto?): SearchResult {
        param?.apply { return appRepository.searchArtist(this) }
        return SearchResult(results = null)
    }
}