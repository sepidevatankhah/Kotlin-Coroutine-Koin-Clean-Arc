package ir.nwise.app.domain.repository

import android.content.Context
import ir.nwise.app.data.Util.NetworkManager.isOnline
import ir.nwise.app.database.AlbumDao
import ir.nwise.app.domain.entities.ArtistSearchDto
import ir.nwise.app.domain.entities.TopAlbumsDto
import ir.nwise.app.domain.model.Album
import ir.nwise.app.domain.model.SearchResult
import ir.nwise.app.domain.model.TopAlbums
import ir.nwise.app.domain.model.UserDto
import ir.nwise.app.domain.model.UserResponse
import ir.nwise.app.networking.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AppRepositoryImp(
    private val apiService: ApiService,
    private val albumDao: AlbumDao,
    private val context: Context
) : AppRepository {

    //repositories from remote:
    override suspend fun getMobileSession(userDto: UserDto): UserResponse {
        return apiService.getMobileSession(
            api_key = userDto.api_key,
            password = userDto.password,
            api_sig = userDto.api_sig,
            username = userDto.username,
            method = userDto.method,
            format = userDto.format
        ).await()
    }

    override suspend fun searchArtist(searchDto: ArtistSearchDto): SearchResult {
        return if (isOnline(context)) {
            with(searchDto) {
                apiService.searchArtist(
                    api_key = apiKey,
                    artist = artist,
                    method = method,
                    format = format,
                    limit = limit.toString(),
                    page = page.toString()
                ).await()
            }
        } else {
            SearchResult(results = null)
        }
    }

    override suspend fun getTopAlbums(searchDto: TopAlbumsDto): TopAlbums {
        return apiService.topAlbums(
            api_key = searchDto.apiKey,
            artist = searchDto.artist,
            method = searchDto.method,
            format = searchDto.format
        ).await()
    }


    //repositories from database:
    override suspend fun saveAlbum(album: Album): Long {
        return withContext(Dispatchers.IO) { albumDao.saveAlbum(album) }
    }

    override suspend fun getAlbumsFromCache(): List<Album> {
        return withContext(Dispatchers.IO) { albumDao.selectAll() }
    }

    override suspend fun getAlbumCountWithName(name: String): Int {
        return withContext(Dispatchers.IO) { albumDao.getAlbumCountWithName(name) }
    }

    override suspend fun deleteAlbum(album: Album): Int {
        return withContext(Dispatchers.IO) { albumDao.deleteAlbum(album) }
    }
}