package com.vmac.giphy.di

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.vmac.giphy.BuildConfig
import com.vmac.giphy.domain.logging.Logger
import com.vmac.giphy.network.client.GiphyClient
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

val retrofitModule = module {
    factory { provideOkHttpClient(logger = get()) }
    single { provideRetrofit(okHttpClient = get()) }
    factory { provideGiphyClient(retrofit = get()) }
}

fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder()
        .baseUrl(API_BASE_URL)
        .client(okHttpClient)
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .addConverterFactory(MoshiConverterFactory.create())
        .build()
}

fun provideOkHttpClient(logger: Logger): OkHttpClient {
    val builder = OkHttpClient.Builder()

    if (BuildConfig.DEBUG) {
        builder.addInterceptor(HttpLoggingInterceptor(object : HttpLoggingInterceptor.Logger {
            override fun log(message: String) {
                logger.d(message = message)
            }
        }).setLevel(HttpLoggingInterceptor.Level.BODY))
    }

    return builder.build()
}

fun provideGiphyClient(retrofit: Retrofit): GiphyClient {
    return retrofit.create(GiphyClient::class.java)
}

private const val API_BASE_URL: String = "https://api.giphy.com"
