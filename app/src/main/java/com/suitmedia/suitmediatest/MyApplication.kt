package com.suitmedia.suitmediatest

import android.app.Application
import com.suitmedia.core.di.networkModule
import com.suitmedia.core.di.repositoryModule
import com.suitmedia.suitmediatest.di.useCaseModule
import com.suitmedia.suitmediatest.di.viewModelModule
import org.koin.core.context.startKoin
import timber.log.Timber

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
        startKoin {
            modules(
                networkModule,
                repositoryModule,
                useCaseModule,
                viewModelModule
            )
        }
    }
}