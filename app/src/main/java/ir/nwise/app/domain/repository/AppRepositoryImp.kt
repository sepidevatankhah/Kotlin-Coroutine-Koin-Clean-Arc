package ir.nwise.app.domain.repository

import android.content.Context
import ir.nwise.app.data.Util.NetworkManager.isOnline
import ir.nwise.app.database.PhotoDao
import ir.nwise.app.domain.model.BasePhoto
import ir.nwise.app.domain.model.PhotoResponse
import ir.nwise.app.networking.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AppRepositoryImp(
    private val apiService: ApiService,
    private val photoDao: PhotoDao,
    private val context: Context
) : AppRepository {
    override suspend fun getPhotoResult(): List<PhotoResponse> {
        return if (isOnline(context)) {
            val response = apiService.getPhotos("", 50, 1).await()
            if (getAllPhotosFromCache().isEmpty())
                savePhotos(response)
            response.photoList
        } else {
            getAllPhotosFromCache()
        }
    }

    override suspend fun savePhotos(basePhoto: BasePhoto): Long {
        if (basePhoto.photoList.isNotEmpty()) {
            for (photo in basePhoto.photoList) {
                withContext(Dispatchers.IO) { photoDao.insertPhoto(photo) }
            }
        }
        return 0L
    }

    override suspend fun getAllPhotosFromCache(): MutableList<PhotoResponse> {
        return withContext(Dispatchers.IO) {
            photoDao.selectAll()
        }
    }
}