package com.applakazam.androidmvvmtemplate.main.di

import com.applakazam.androidmvvmtemplate.api.DefaultResponseInterpreter
import com.applakazam.androidmvvmtemplate.api.ResponseInterpreter
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 *  Created by paulbisioc on 22.05.2023
 */

@Module
@InstallIn(SingletonComponent::class)
class ResponseInterpreterModule {

    @Singleton
    @Provides
    fun provideResponseInterpreter(moshi: Moshi): ResponseInterpreter =
        DefaultResponseInterpreter(moshi)
}