package com.applakazam.androidmvvmtemplate.di

import android.content.Context
import com.applakazam.androidmvvmtemplate.common.Constants
import com.applakazam.androidmvvmtemplate.common.network.NetworkConnectionInterceptor
import com.applakazam.androidmvvmtemplate.common.network.NetworkMonitor
import com.applakazam.androidmvvmtemplate.common.network.NetworkMonitorImpl
import com.applakazam.androidmvvmtemplate.common.structure.api.ServiceApi
import com.applakazam.androidmvvmtemplate.data.posts.GetPostsEntity
import com.applakazam.androidmvvmtemplate.data.users.GetUsersEntity
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApiModule {
    @Provides
    fun provideOkHttpClient(
        loggingInterceptor: Interceptor,
        networkConnectionInterceptor: NetworkConnectionInterceptor,
    ): OkHttpClient =
        OkHttpClient.Builder()
            .connectTimeout(25, TimeUnit.SECONDS)
            .writeTimeout(25, TimeUnit.SECONDS)
            .readTimeout(25, TimeUnit.SECONDS)
            .addInterceptor(loggingInterceptor)
            .addInterceptor(networkConnectionInterceptor)
            .build()

    @Provides
    fun provideMoshi(): Moshi = Moshi.Builder()
        .add(GetUsersEntity.GetUsersAdapter())
        .add(GetPostsEntity.GetPostsAdapter())
        .addLast(KotlinJsonAdapterFactory())
        .build()

    @Provides
    fun provideLoggingInterceptor(): Interceptor =
        HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }

    @Provides
    @Singleton
    fun provideNetworkMonitor(@ApplicationContext context: Context): NetworkMonitor {
        return NetworkMonitorImpl(context)
    }

    @Provides
    @Singleton
    fun provideNetworkConnectionInterceptor(networkMonitor: NetworkMonitor): NetworkConnectionInterceptor {
        return NetworkConnectionInterceptor(networkMonitor)
    }

    @Provides
    @Singleton
    fun provideServiceApi(
        okHttpClient: OkHttpClient,
        moshi: Moshi
    ): ServiceApi = Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()
        .create(ServiceApi::class.java)
}