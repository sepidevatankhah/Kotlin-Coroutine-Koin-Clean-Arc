package ir.nwise.app.domain.repository

import ir.nwise.app.domain.entities.ArtistSearchDto
import ir.nwise.app.domain.entities.TopAlbumsDto
import ir.nwise.app.domain.model.Album
import ir.nwise.app.domain.model.SearchResult
import ir.nwise.app.domain.model.TopAlbums
import ir.nwise.app.domain.model.UserDto
import ir.nwise.app.domain.model.UserResponse

interface AppRepository {
    //remote functions
    suspend fun getMobileSession(userDto: UserDto): UserResponse
    suspend fun searchArtist(searchDto: ArtistSearchDto): SearchResult
    suspend fun getTopAlbums(searchDto: TopAlbumsDto): TopAlbums

    //db functions
    suspend fun saveAlbum(album: Album): Long
    suspend fun getAlbumsFromCache(): List<Album>
    suspend fun getAlbumCountWithName(name: String): Int
    suspend fun deleteAlbum(album: Album): Int
}