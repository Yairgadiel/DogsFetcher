package com.gy.demo.di

import android.content.Context
import androidx.room.Room
import com.gy.demo.dogsScreen.model.local.AppDatabase
import com.gy.demo.dogsScreen.model.local.DogsDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {
    @Provides
    fun provideChannelDao(appDatabase: AppDatabase): DogsDao {
        return appDatabase.dogsDao()
    }

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            "DogsDB"
        ).build()
    }
}