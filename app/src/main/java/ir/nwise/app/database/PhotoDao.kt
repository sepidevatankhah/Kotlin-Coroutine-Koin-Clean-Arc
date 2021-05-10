package ir.nwise.app.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ir.nwise.app.domain.model.PhotoResponse

@Dao
interface PhotoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPhoto(photo: PhotoResponse): Long

    @Delete
    suspend fun deletePhoto(photo: PhotoResponse): Int

    @Query("SELECT * from Photos")
    suspend fun selectAll(): MutableList<PhotoResponse>
}