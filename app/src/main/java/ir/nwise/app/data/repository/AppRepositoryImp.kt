package ir.nwise.app.data.repository

import android.content.Context
import ir.nwise.app.utils.NetworkManager.isOnline
import ir.nwise.app.data.source.database.LocalDataSource
import ir.nwise.app.data.source.remote.RemoteDataSource
import ir.nwise.app.domain.entities.ArtistSearchDto
import ir.nwise.app.domain.entities.TopAlbumsDto
import ir.nwise.app.domain.model.Album
import ir.nwise.app.domain.model.SearchResult
import ir.nwise.app.domain.model.TopAlbums
import ir.nwise.app.domain.model.UserDto
import ir.nwise.app.domain.model.UserResponse
import ir.nwise.app.domain.usecase.base.DefaultDispatcherProvider
import ir.nwise.app.domain.usecase.base.DispatcherProvider
import kotlinx.coroutines.withContext

class AppRepositoryImp(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val context: Context,
    private val dispatchers: DispatcherProvider = DefaultDispatcherProvider()
) : AppRepository {

    //repositories from remote:
    override suspend fun getMobileSession(userDto: UserDto): UserResponse {
        return remoteDataSource.getMobileSession(userDto)
    }

    override suspend fun searchArtist(searchDto: ArtistSearchDto): SearchResult {
        return if (isOnline(context)) {
            remoteDataSource.searchArtist(searchDto)
        } else {
            SearchResult(results = null)
        }
    }

    override suspend fun getTopAlbums(searchDto: TopAlbumsDto): TopAlbums {
        return remoteDataSource.getTopAlbums(searchDto)
    }


    //repositories from database:
    override suspend fun saveAlbum(album: Album): Long {
        return withContext(dispatchers.io()) { localDataSource.saveAlbum(album) }
    }

    override suspend fun getAlbumsFromCache(): List<Album> {
        return withContext(dispatchers.io()) { localDataSource.getAlbumsFromCache() }
    }

    override suspend fun getAlbumCountWithName(name: String): Int {
        return withContext(dispatchers.io()) { localDataSource.getAlbumCountWithName(name) }
    }

    override suspend fun deleteAlbum(album: Album): Int {
        return withContext(dispatchers.io()) { localDataSource.deleteAlbum(album) }
    }
}