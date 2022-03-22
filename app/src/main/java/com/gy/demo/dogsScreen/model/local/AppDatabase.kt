package com.gy.demo.dogsScreen.model.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.gy.demo.dogsScreen.model.Dog

@Database(entities = [Dog::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun dogsDao(): DogsDao
}