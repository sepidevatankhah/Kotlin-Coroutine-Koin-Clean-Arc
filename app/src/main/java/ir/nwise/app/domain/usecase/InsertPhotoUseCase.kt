package ir.nwise.app.domain.usecase

import ir.nwise.app.domain.model.BasePhoto
import ir.nwise.app.domain.repository.AppRepository
import ir.nwise.app.domain.usecase.base.UseCase

class InsertPhotoUseCase(private val appRepository: AppRepository) : UseCase<Long>() {
    var basePhoto = BasePhoto(1, 50, listOf())
    override suspend fun executeOnBackground(): Long {
        return appRepository.savePhotos(basePhoto)
    }
}