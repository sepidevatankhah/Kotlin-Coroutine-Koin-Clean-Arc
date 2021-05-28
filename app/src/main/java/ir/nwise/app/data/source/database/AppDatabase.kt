package ir.nwise.app.data.source.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import ir.nwise.app.data.source.database.converters.ArtistConverter
import ir.nwise.app.data.source.database.converters.ArtistImageConverter
import ir.nwise.app.domain.model.Album
import ir.nwise.app.domain.model.Artist
import ir.nwise.app.domain.model.ArtistImage

@Database(
    entities = [Album::class, Artist::class, ArtistImage::class],
    version = AppDatabase.VERSION
)
@TypeConverters(ArtistImageConverter::class, ArtistConverter::class)
abstract class AppDatabase : RoomDatabase() {
    companion object {
        const val DB_NAME = "music.db"
        const val VERSION = 1
    }

    abstract fun albumDao(): AlbumDao
}