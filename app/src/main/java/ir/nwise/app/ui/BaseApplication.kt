package ir.nwise.app.ui

import android.app.Application
import ir.nwise.app.di.databaseModule
import ir.nwise.app.di.domainModule
import ir.nwise.app.di.networkModule
import ir.nwise.app.di.uiModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class BaseApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@BaseApplication)
            modules(
                listOf(
                    uiModule,
                    networkModule,
                    domainModule,
                    databaseModule
                )
            )
        }
    }
}