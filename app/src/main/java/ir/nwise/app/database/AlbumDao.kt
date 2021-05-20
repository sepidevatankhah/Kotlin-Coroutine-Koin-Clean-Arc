package ir.nwise.app.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ir.nwise.app.domain.model.Album

@Dao
interface AlbumDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveAlbum(album: Album): Long

    @Delete
    suspend fun deleteAlbum(album: Album): Int

    //To show cached Albums in order to the last one that is saved
    @Query("SELECT * from Album order by 1 DESC")
    suspend fun selectAll(): MutableList<Album>

    @Query("SELECT count(id) from Album where name = :albumName ")
    suspend fun getAlbumCountWithName(albumName: String): Int
}