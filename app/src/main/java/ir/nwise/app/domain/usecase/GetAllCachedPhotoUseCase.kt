package ir.nwise.app.domain.usecase

import ir.nwise.app.domain.model.PhotoResponse
import ir.nwise.app.domain.repository.AppRepository
import ir.nwise.app.domain.usecase.base.UseCase

class GetAllCachedPhotoUseCase(private val appRepository: AppRepository):UseCase<MutableList<PhotoResponse>>() {
    override suspend fun executeOnBackground(): MutableList<PhotoResponse> {
        return appRepository.getAllPhotosFromCache()
    }
}