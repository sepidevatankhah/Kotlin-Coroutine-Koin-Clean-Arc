package ir.nwise.app.domain.entities

import ir.nwise.app.domain.entities.base.BaseDto
import ir.nwise.app.networking.Config

data class ArtistSearchDto(
    override val method: String = MethodType.ARTIST_SEARCH.value,
    override val format: String = Config.FORMAT,
    override val apiKey: String = Config.API_KEY,
    val artist: String,
    val limit: Int = 50,
    val page: Int = 1
) : BaseDto

data class TopAlbumsDto(
    override val method: String = MethodType.ARTIST_TOP_ALBUMS.value,
    override val format: String = Config.FORMAT,
    override val apiKey: String = Config.API_KEY,
    val artist: String,
    val limit: Int = 20,
    val page: Int = 1
) : BaseDto

enum class MethodType(val value: String) {
    ARTIST_SEARCH("artist.search"),
    ARTIST_TOP_ALBUMS("artist.gettopalbums")
}