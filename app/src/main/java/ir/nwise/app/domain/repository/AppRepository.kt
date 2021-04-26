package ir.nwise.app.domain.repository

import ir.nwise.app.domain.model.BasePhoto

interface AppRepository {
    suspend fun getFoodResult(): BasePhoto
}