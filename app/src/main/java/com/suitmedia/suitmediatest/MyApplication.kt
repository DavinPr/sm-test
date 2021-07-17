package com.suitmedia.suitmediatest

import android.app.Application
import com.suitmedia.core.di.networkModule
import com.suitmedia.core.di.repositoryModule
import com.suitmedia.suitmediatest.di.useCaseModule
import com.suitmedia.suitmediatest.di.viewModelModule
import org.koin.core.context.startKoin

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
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