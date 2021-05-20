package ir.nwise.app.di

import android.content.Context
import ir.nwise.app.database.AlbumDao
import ir.nwise.app.domain.repository.AppRepository
import ir.nwise.app.domain.repository.AppRepositoryImp
import ir.nwise.app.domain.usecase.DeleteAlbumUseCase
import ir.nwise.app.domain.usecase.GetAlbumCountWithNameUseCase
import ir.nwise.app.domain.usecase.GetAlbumsFromCacheUseCase
import ir.nwise.app.domain.usecase.GetMobileSessionUseCase
import ir.nwise.app.domain.usecase.SaveAlbumUseCase
import ir.nwise.app.domain.usecase.SearchArtistUseCase
import ir.nwise.app.domain.usecase.TopAlbumsUseCase
import ir.nwise.app.networking.ApiService
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val domainModule = module {
    fun provideRepository(api: ApiService, dao: AlbumDao, context: Context): AppRepository {
        return AppRepositoryImp(api, dao, context)
    }

    factory { GetMobileSessionUseCase(get()) }
    factory { SearchArtistUseCase(get()) }
    factory { TopAlbumsUseCase(get()) }
    factory { SaveAlbumUseCase(get()) }
    factory { GetAlbumCountWithNameUseCase(get()) }
    factory { GetAlbumsFromCacheUseCase(get()) }
    factory { DeleteAlbumUseCase(get()) }

    single { provideRepository(get(), get(), androidContext()) }
}