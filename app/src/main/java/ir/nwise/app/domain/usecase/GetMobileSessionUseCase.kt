package ir.nwise.app.domain.usecase

import ir.nwise.app.domain.model.UserDto
import ir.nwise.app.domain.model.UserResponse
import ir.nwise.app.data.repository.AppRepository
import ir.nwise.app.domain.usecase.base.UseCase

class GetMobileSessionUseCase(private val appRepository: AppRepository) :
    UseCase<UserDto, UserResponse>() {
    override suspend fun executeOnBackground(param: UserDto?): UserResponse {
        param?.apply { return appRepository.getMobileSession(param) }
        return UserResponse(session = null)
    }
}