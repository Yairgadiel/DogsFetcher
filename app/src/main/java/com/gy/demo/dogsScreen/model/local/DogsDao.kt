package com.gy.demo.dogsScreen.model.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.gy.demo.dogsScreen.model.Dog
import kotlinx.coroutines.flow.Flow

@Dao
interface DogsDao {
    @Query("SELECT * FROM Dog ORDER BY timestamp DESC")
    fun getAllDogs(): Flow<List<Dog>>

    @Insert
    suspend fun insertDog(dog: Dog)
}