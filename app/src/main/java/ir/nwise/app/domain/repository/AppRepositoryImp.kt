package ir.nwise.app.domain.repository

import ir.nwise.app.domain.model.BasePhoto

class AppRepositoryImp(private val foodRepositoryImp: FoodRepositoryImp) : AppRepository {
    override suspend fun getFoodResult(): BasePhoto {
        return foodRepositoryImp.getFoodResult()
    }
}