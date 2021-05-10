package ir.nwise.app.di

import ir.nwise.app.networking.provideApiService
import ir.nwise.app.networking.provideLoggingInterceptor
import ir.nwise.app.networking.provideOkHttpClient
import ir.nwise.app.networking.provideRetrofit
import ir.nwise.app.networking.providesGsonConverterFactory
import org.koin.dsl.module

val networkModule = module {
    factory { provideOkHttpClient(get()) }
    factory { provideApiService(get()) }
    factory { provideLoggingInterceptor() }
    single { providesGsonConverterFactory() }
    single { provideRetrofit(get()) }
}

