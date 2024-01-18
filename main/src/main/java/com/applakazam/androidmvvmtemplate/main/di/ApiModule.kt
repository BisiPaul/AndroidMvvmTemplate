package com.applakazam.androidmvvmtemplate.main.di

import android.content.Context
import com.applakazam.androidmvvmtemplate.api.features.contacts.ContactsApiConstants
import com.applakazam.androidmvvmtemplate.api.features.contacts.ContactsServiceApi
import com.applakazam.androidmvvmtemplate.api.features.contacts.data.posts.GetPostsEntity
import com.applakazam.androidmvvmtemplate.api.features.contacts.data.users.GetUsersEntity
import com.applakazam.androidmvvmtemplate.api.network.NetworkConnectionInterceptor
import com.applakazam.androidmvvmtemplate.api.network.NetworkMonitor
import com.applakazam.androidmvvmtemplate.api.network.NetworkMonitorImpl
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
    fun provideContactsServiceApi(
        okHttpClient: OkHttpClient,
        moshi: Moshi
    ): ContactsServiceApi = Retrofit.Builder()
        .baseUrl(ContactsApiConstants.BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()
        .create(ContactsServiceApi::class.java)
}