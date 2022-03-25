package com.gy.demo.dogs.model.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.gy.demo.dogs.model.Dog
import kotlinx.coroutines.flow.Flow

@Dao
interface DogsDao {
    @Query("SELECT * FROM Dog ORDER BY timestamp DESC")
    fun getAllDogs(): Flow<List<Dog>>

    @Query("SELECT * FROM Dog WHERE uid = :uid")
    fun getDogById(uid: Int): Flow<Dog>

    @Insert
    suspend fun insertDog(dog: Dog) : Long

    @Delete
    suspend fun deleteDog(dog: Dog)
}