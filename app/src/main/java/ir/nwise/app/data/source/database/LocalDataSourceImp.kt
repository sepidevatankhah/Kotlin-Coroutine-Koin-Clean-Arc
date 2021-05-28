package ir.nwise.app.data.source.database

import ir.nwise.app.domain.model.Album
import ir.nwise.app.domain.usecase.base.DefaultDispatcherProvider
import ir.nwise.app.domain.usecase.base.DispatcherProvider
import kotlinx.coroutines.withContext

class LocalDataSourceImp(
    private val albumDao: AlbumDao,
    private val dispatchers: DispatcherProvider = DefaultDispatcherProvider()
) : LocalDataSource {
    override suspend fun saveAlbum(album: Album): Long {
        return withContext(dispatchers.io()) { albumDao.saveAlbum(album) }
    }

    override suspend fun getAlbumsFromCache(): List<Album> {
        return withContext(dispatchers.io()) { albumDao.selectAll() }
    }

    override suspend fun getAlbumCountWithName(name: String): Int {
        return withContext(dispatchers.io()) { albumDao.getAlbumCountWithName(name) }
    }

    override suspend fun deleteAlbum(album: Album): Int {
        return withContext(dispatchers.io()) { albumDao.deleteAlbum(album) }
    }
}