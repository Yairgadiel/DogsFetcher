package com.gy.demo.di

import com.gy.demo.dogs.model.DogsRepositoryImpl
import com.gy.demo.dogs.model.IDogsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Singleton
    @Provides
    fun provideDogsRepository(dogsRepositoryImpl: DogsRepositoryImpl) : IDogsRepository {
        return dogsRepositoryImpl
    }
}