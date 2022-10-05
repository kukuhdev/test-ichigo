package com.eve.testichigo.core.di

import com.eve.testichigo.BuildConfig
import com.eve.testichigo.core.utils.RequestInterceptor
import com.eve.testichigo.core.utils.SharedPrefs
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideRetrofit(okHttp: OkHttpClient) : Retrofit {
        return Retrofit.Builder().apply {
            addConverterFactory(GsonConverterFactory.create())
            client(okHttp)
            baseUrl(BuildConfig.BASE_URL)
        }.build()
    }

    @Singleton
    @Provides
    fun provideOkHttp(requestInterceptor: RequestInterceptor) : OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        val authInterceptor = Interceptor {
            val original = it.request()
            val newRequest = original.newBuilder()
                .header("Content-Type", "application/json")
                .header("Authorization", BuildConfig.ACCESS_KEY)

            it.proceed(newRequest.build())
        }

        return OkHttpClient.Builder().apply {
            retryOnConnectionFailure(true)
            connectTimeout(60, TimeUnit.SECONDS)
            readTimeout(60, TimeUnit.SECONDS)
            writeTimeout(60, TimeUnit.SECONDS)
            addInterceptor(requestInterceptor)
            addNetworkInterceptor(loggingInterceptor)
            addInterceptor(authInterceptor)
        }.build()

    }

    @Provides
    fun provideRequestInterceptor(prefs: SharedPrefs) : RequestInterceptor {
        return RequestInterceptor(prefs)
    }

}