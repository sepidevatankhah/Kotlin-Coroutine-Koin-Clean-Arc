package ir.nwise.app.domain.usecase

import ir.nwise.app.domain.entities.TopAlbumsDto
import ir.nwise.app.domain.model.TopAlbum
import ir.nwise.app.domain.model.TopAlbums
import ir.nwise.app.domain.repository.AppRepository
import ir.nwise.app.domain.usecase.base.UseCase

class TopAlbumsUseCase(private val appRepository: AppRepository) :
    UseCase<TopAlbumsDto, TopAlbums>() {
    override suspend fun executeOnBackground(param: TopAlbumsDto?): TopAlbums {
        param?.apply { return appRepository.getTopAlbums(this) }
        return TopAlbums(topAlbums = TopAlbum(albums = listOf()))
    }
}