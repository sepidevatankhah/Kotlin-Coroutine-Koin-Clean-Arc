package ir.nwise.app.di

import android.content.Context
import ir.nwise.app.networking.ApiService
import ir.nwise.app.database.PhotoDao
import ir.nwise.app.domain.repository.AppRepository
import ir.nwise.app.domain.repository.AppRepositoryImp
import ir.nwise.app.domain.usecase.GetAllCachedPhotoUseCase
import ir.nwise.app.domain.usecase.GetPhotoResultUseCase
import ir.nwise.app.domain.usecase.InsertPhotoUseCase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val domainModule = module {
    fun provideRepository(api: ApiService, dao: PhotoDao, context: Context): AppRepository {
        return AppRepositoryImp(api, dao, context)
    }

    factory { GetPhotoResultUseCase(get()) }
    factory { GetAllCachedPhotoUseCase(get()) }
    factory { InsertPhotoUseCase(get()) }

    single { provideRepository(get(), get(), androidContext()) }
}