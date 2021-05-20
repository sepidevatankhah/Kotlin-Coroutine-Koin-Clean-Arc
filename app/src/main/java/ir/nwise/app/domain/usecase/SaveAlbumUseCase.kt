package ir.nwise.app.domain.usecase

import ir.nwise.app.domain.model.Album
import ir.nwise.app.domain.repository.AppRepository
import ir.nwise.app.domain.usecase.base.UseCase

class SaveAlbumUseCase(private val appRepository: AppRepository) : UseCase<Album, Long>() {
    override suspend fun executeOnBackground(param: Album?): Long {
        param?.apply { return appRepository.saveAlbum(this) }
        return 0L
    }
}