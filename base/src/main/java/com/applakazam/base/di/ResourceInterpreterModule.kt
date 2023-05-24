package com.applakazam.base.di

import com.applakazam.base.common.DefaultResponseInterpreter
import com.applakazam.base.common.ResponseInterpreter
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
class ResourceInterpreterModule {

    @Singleton
    @Provides
    fun provideResourceInterpreter(moshi: Moshi): ResponseInterpreter =
        DefaultResponseInterpreter(moshi)
}