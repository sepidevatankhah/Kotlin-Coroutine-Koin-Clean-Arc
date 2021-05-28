package ir.nwise.app.data.source.database

import ir.nwise.app.domain.model.Album

interface LocalDataSource {
    suspend fun saveAlbum(album: Album): Long
    suspend fun getAlbumsFromCache(): List<Album>
    suspend fun getAlbumCountWithName(name: String): Int
    suspend fun deleteAlbum(album: Album): Int
}