package ir.nwise.app.networking

import org.koin.dsl.module

val networkModule = module {
    factory { provideOkHttpClient(get()) }
    factory { provideApiService(get()) }
    factory { provideLoggingInterceptor() }
    single { providesGsonConverterFactory() }
    single { provideRetrofit(get()) }
}