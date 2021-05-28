package ir.nwise.app.domain.usecase

import ir.nwise.app.domain.model.Album
import ir.nwise.app.data.repository.AppRepository
import ir.nwise.app.domain.usecase.base.UseCase

class DeleteAlbumUseCase(private val appRepository: AppRepository) : UseCase<Album, Int>() {
    override suspend fun executeOnBackground(param: Album?): Int {
        param?.apply { return appRepository.deleteAlbum(this) }
        return 0
    }
}