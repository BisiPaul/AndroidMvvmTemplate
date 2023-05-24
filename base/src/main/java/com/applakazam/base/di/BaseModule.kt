package com.applakazam.base.di

import com.applakazam.base.error.BaseErrorTranslator
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/**
 *  Created by paulbisioc on 24.05.2023
 */
@Module
@InstallIn(SingletonComponent::class)
class BaseModule {

    @Provides
    fun provideBaseErrorTranslator() : BaseErrorTranslator = BaseErrorTranslator()
}