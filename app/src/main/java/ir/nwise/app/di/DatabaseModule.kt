package ir.nwise.app.di

import android.app.Application
import androidx.room.Room
import ir.nwise.app.database.AppDatabase
import ir.nwise.app.database.PhotoDao
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val databaseModule = module {

    fun provideRoomDatabase(application: Application): AppDatabase {
        return Room.databaseBuilder(application, AppDatabase::class.java, AppDatabase.DB_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }

    fun providePhotoDao(db: AppDatabase): PhotoDao {
        return db.photoDao()
    }

    single { provideRoomDatabase(androidApplication()) }
    single { providePhotoDao(get()) }
}