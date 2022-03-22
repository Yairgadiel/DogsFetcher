package com.gy.demo.di

import com.gy.demo.dogsScreen.model.DogsRepositoryImpl
import com.gy.demo.dogsScreen.model.IDogsRepository
import com.gy.demo.dogsScreen.model.local.DogsDao
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindDogsRepository(dogsRepositoryImpl: DogsRepositoryImpl): IDogsRepository

}