package com.vfalin.sibext.di.modules

import com.vfalin.sibext.network.LoggingInterceptor
import com.vfalin.sibext.utils.Constants
import okhttp3.OkHttpClient
import org.koin.dsl.bind
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class AppModule {
    val module = module {
        single {
            Retrofit.Builder()
                .baseUrl(Constants.FILMS_BASE_URL)
                .client(
                    OkHttpClient()
                        .newBuilder()
                        .addNetworkInterceptor(LoggingInterceptor())
                        .build()
                )
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        } bind Retrofit::class
    }
}