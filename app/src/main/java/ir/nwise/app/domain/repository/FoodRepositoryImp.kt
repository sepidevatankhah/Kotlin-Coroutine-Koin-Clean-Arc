package ir.nwise.app.domain.repository

import ir.nwise.app.domain.model.BasePhoto
import ir.nwise.app.networking.ApiService


class FoodRepositoryImp(private val service: ApiService) {

    suspend fun getFoodResult(): BasePhoto {
        return service.getPhotos("", 50, 1).await()
    }
}