package com.applakazam.androidmvvmtemplate.main.di

import com.applakazam.androidmvvmtemplate.main.common.repositories.UsersRepository
import com.applakazam.androidmvvmtemplate.main.common.repositories.UsersRepositoryImpl
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