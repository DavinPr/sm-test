package com.suitmedia.core.di

import com.suitmedia.core.data.AppRepository
import com.suitmedia.core.data.source.dummy.DummyDataSource
import com.suitmedia.core.data.source.remote.RemoteDataSource
import com.suitmedia.core.data.source.remote.network.ApiService
import com.suitmedia.core.domain.repository.IAppRepository
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val networkModule = module {
    single {
        OkHttpClient.Builder()
            .readTimeout(120, TimeUnit.SECONDS)
            .writeTimeout(120, TimeUnit.SECONDS)
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .build()
    }

    single {
        val retrofit = Retrofit.Builder()
            .baseUrl("http://www.mocky.io/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
        retrofit.create(ApiService::class.java)
    }
}

val repositoryModule = module {
    single { RemoteDataSource(get()) }
    single { DummyDataSource() }
    single<IAppRepository> { AppRepository(get(), get()) }
}