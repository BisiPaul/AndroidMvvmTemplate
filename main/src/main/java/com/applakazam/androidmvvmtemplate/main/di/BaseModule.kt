package com.applakazam.androidmvvmtemplate.main.di

import com.applakazam.androidmvvmtemplate.api.error.BaseErrorTranslator
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