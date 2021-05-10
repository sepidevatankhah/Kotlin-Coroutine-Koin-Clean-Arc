package ir.nwise.app.domain.repository

import ir.nwise.app.domain.model.BasePhoto
import ir.nwise.app.domain.model.PhotoResponse

interface AppRepository {
    suspend fun getPhotoResult(): List<PhotoResponse>
    suspend fun savePhotos(basePhoto: BasePhoto):Long
    suspend fun getAllPhotosFromCache() : MutableList<PhotoResponse>

}