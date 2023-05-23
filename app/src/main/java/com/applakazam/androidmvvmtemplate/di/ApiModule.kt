package com.applakazam.androidmvvmtemplate.di

import com.applakazam.androidmvvmtemplate.common.Constants
import com.applakazam.androidmvvmtemplate.common.structure.api.ServiceApi
import com.applakazam.androidmvvmtemplate.data.posts.GetPostsEntity
import com.applakazam.androidmvvmtemplate.data.users.GetUsersEntity
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
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
    ): OkHttpClient =
        OkHttpClient.Builder()
            .connectTimeout(25, TimeUnit.SECONDS)
            .writeTimeout(25, TimeUnit.SECONDS)
            .readTimeout(25, TimeUnit.SECONDS)
            .addInterceptor(loggingInterceptor)
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