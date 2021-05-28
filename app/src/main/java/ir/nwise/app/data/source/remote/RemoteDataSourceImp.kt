package ir.nwise.app.data.source.remote

import ir.nwise.app.domain.entities.ArtistSearchDto
import ir.nwise.app.domain.entities.TopAlbumsDto
import ir.nwise.app.domain.model.SearchResult
import ir.nwise.app.domain.model.TopAlbums
import ir.nwise.app.domain.model.UserDto
import ir.nwise.app.domain.model.UserResponse
import ir.nwise.app.networking.ApiService

class RemoteDataSourceImp(private val apiService: ApiService) : RemoteDataSource {
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
        return with(searchDto) {
            apiService.searchArtist(
                api_key = apiKey,
                artist = artist,
                method = method,
                format = format,
                limit = limit.toString(),
                page = page.toString()
            ).await()
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
}