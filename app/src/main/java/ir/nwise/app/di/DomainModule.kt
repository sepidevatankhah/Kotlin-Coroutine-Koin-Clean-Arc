package ir.nwise.app.di

import android.content.Context
import ir.nwise.app.data.source.database.AlbumDao
import ir.nwise.app.data.source.database.LocalDataSource
import ir.nwise.app.data.source.database.LocalDataSourceImp
import ir.nwise.app.data.source.remote.RemoteDataSource
import ir.nwise.app.data.source.remote.RemoteDataSourceImp
import ir.nwise.app.data.repository.AppRepository
import ir.nwise.app.data.repository.AppRepositoryImp
import ir.nwise.app.domain.usecase.DeleteAlbumUseCase
import ir.nwise.app.domain.usecase.GetAlbumCountWithNameUseCase
import ir.nwise.app.domain.usecase.GetAlbumsFromCacheUseCase
import ir.nwise.app.domain.usecase.GetMobileSessionUseCase
import ir.nwise.app.domain.usecase.SaveAlbumUseCase
import ir.nwise.app.domain.usecase.SearchArtistUseCase
import ir.nwise.app.domain.usecase.TopAlbumsUseCase
import ir.nwise.app.domain.usecase.base.DefaultDispatcherProvider
import ir.nwise.app.domain.usecase.base.DispatcherProvider
import ir.nwise.app.networking.ApiService
import kotlinx.coroutines.MainScope
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val domainModule = module {

    fun provideLocalDataSource(
        albumDao: AlbumDao,
        dispatcher: DispatcherProvider
    ): LocalDataSource {
        return LocalDataSourceImp(albumDao, dispatcher)
    }

    fun provideRemoteDataSource(apiService: ApiService): RemoteDataSource {
        return RemoteDataSourceImp(apiService)
    }

    fun provideRepository(
        remoteDataSource: RemoteDataSource,
        localDataSource: LocalDataSource,
        context: Context,
        dispatcher: DispatcherProvider
    ): AppRepository {
        return AppRepositoryImp(remoteDataSource, localDataSource, context, dispatcher)
    }

    factory { GetMobileSessionUseCase(get()) }
    factory {
        SearchArtistUseCase(
            appRepository = get(),
            coroutineScope = MainScope(),
            dispatchers = DefaultDispatcherProvider()
        )
    }
    factory { TopAlbumsUseCase(get()) }
    factory { SaveAlbumUseCase(get()) }
    factory { GetAlbumCountWithNameUseCase(get()) }
    factory { GetAlbumsFromCacheUseCase(get()) }
    factory { DeleteAlbumUseCase(get()) }

    single { provideRemoteDataSource(get()) }
    single { provideLocalDataSource(get(), DefaultDispatcherProvider()) }
    single { provideRepository(get(), get(), androidContext(), DefaultDispatcherProvider()) }
}