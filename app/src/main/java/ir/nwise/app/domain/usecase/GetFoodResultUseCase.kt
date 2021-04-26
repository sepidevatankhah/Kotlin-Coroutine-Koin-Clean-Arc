package ir.nwise.app.domain.usecase

import ir.nwise.app.domain.model.BasePhoto
import ir.nwise.app.domain.repository.AppRepository
import ir.nwise.app.domain.usecase.base.UseCase


class GetFoodResultUseCase(private val appRepository: AppRepository) : UseCase<BasePhoto>() {
    override suspend fun executeOnBackground(): BasePhoto {
        return appRepository.getFoodResult()
    }
}