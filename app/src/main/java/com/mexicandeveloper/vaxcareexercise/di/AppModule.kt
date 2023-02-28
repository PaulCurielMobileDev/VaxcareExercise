package com.mexicandeveloper.vaxcareexercise.di

import com.mexicandeveloper.vaxcareexercise.BuildConfig
import com.mexicandeveloper.vaxcareexercise.api.ApiHelper
import com.mexicandeveloper.vaxcareexercise.api.ApiHelperImpl
import com.mexicandeveloper.vaxcareexercise.api.ApiService
import com.mexicandeveloper.vaxcareexercise.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Qualifier
import javax.inject.Singleton


@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class AuthInterceptorOkHttpClient

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class DebugInterceptorOkHttpClient

@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Provides
    fun provideBaseUrl() = Constants.BASE_URL

    @Provides
    fun provideDebugInterceptor(): Interceptor {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        return loggingInterceptor
    }

    @AuthInterceptorOkHttpClient
    @Provides
    fun provideAuthInterceptorOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder().build()
    }

    @DebugInterceptorOkHttpClient
    @Provides
    fun provideDebugInterceptorOkHttpClient(interceptor: Interceptor): OkHttpClient {
        return OkHttpClient.Builder().addInterceptor(interceptor).build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(
        @AuthInterceptorOkHttpClient okHttpClientAuth: OkHttpClient,
        @DebugInterceptorOkHttpClient okHttpClientDebug: OkHttpClient,
        BASE_URL: String
    ): Retrofit = Retrofit
        .Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .client(if (BuildConfig.DEBUG) okHttpClientDebug else okHttpClientAuth)
        .build()

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit) = retrofit.create<ApiService>()

    @Provides
    @Singleton
    fun provideApiHelper(apiHelper: ApiHelperImpl): ApiHelper = apiHelper

}