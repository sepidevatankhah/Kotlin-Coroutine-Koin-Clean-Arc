package ir.nwise.app.domain.usecase

import ir.nwise.app.domain.model.Album
import ir.nwise.app.domain.repository.AppRepository
import ir.nwise.app.domain.usecase.base.UseCase

class GetAlbumsFromCacheUseCase(private val appRepository: AppRepository) :
    UseCase<Any, List<Album>>() {
    override suspend fun executeOnBackground(param: Any?): List<Album> {
        return appRepository.getAlbumsFromCache()
    }
}