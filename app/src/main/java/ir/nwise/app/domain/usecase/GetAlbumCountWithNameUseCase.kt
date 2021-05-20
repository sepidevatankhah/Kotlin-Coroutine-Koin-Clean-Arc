package ir.nwise.app.domain.usecase

import ir.nwise.app.domain.repository.AppRepository
import ir.nwise.app.domain.usecase.base.UseCase
import kotlin.apply as apply1

class GetAlbumCountWithNameUseCase(private val appRepository: AppRepository) :
    UseCase<String, Int>() {
    override suspend fun executeOnBackground(param: String?): Int {
        param?.apply1 { return appRepository.getAlbumCountWithName(param) }
        return 0
    }
}