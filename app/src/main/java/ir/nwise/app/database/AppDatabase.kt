package ir.nwise.app.database

import androidx.room.Database
import androidx.room.RoomDatabase
import ir.nwise.app.domain.model.PhotoResponse

@Database(entities = [PhotoResponse::class], version = AppDatabase.VERSION)
abstract class AppDatabase : RoomDatabase() {
    companion object{
        const val DB_NAME ="photos.db"
        const val VERSION = 1
    }

    abstract fun photoDao() : PhotoDao
}