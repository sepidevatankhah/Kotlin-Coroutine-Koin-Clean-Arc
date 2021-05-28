package ir.nwise.app.data.source.remote

import ir.nwise.app.domain.entities.ArtistSearchDto
import ir.nwise.app.domain.entities.TopAlbumsDto
import ir.nwise.app.domain.model.SearchResult
import ir.nwise.app.domain.model.TopAlbums
import ir.nwise.app.domain.model.UserDto
import ir.nwise.app.domain.model.UserResponse

interface RemoteDataSource {
    //remote functions
    suspend fun getMobileSession(userDto: UserDto): UserResponse
    suspend fun searchArtist(searchDto: ArtistSearchDto): SearchResult
    suspend fun getTopAlbums(searchDto: TopAlbumsDto): TopAlbums
}