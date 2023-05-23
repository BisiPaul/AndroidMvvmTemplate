package com.applakazam.androidmvvmtemplate.di

import com.applakazam.androidmvvmtemplate.common.repositories.UsersRepository
import com.applakazam.androidmvvmtemplate.common.repositories.UsersRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

/**
 *  Created by paulbisioc on 22.05.2023
 */

@Module
@InstallIn(ActivityRetainedComponent::class)
abstract class UsersRepositoriesModule {

    @Binds
    abstract fun provideUsersRepository(impl: UsersRepositoryImpl): UsersRepository
}