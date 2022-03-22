package com.gy.demo.dogsScreen.model

import com.gy.demo.dogsScreen.model.network.DogResponse
import kotlinx.coroutines.flow.Flow

interface IDogsRepository {
    suspend fun fetchNewDog() : DogResponse

    fun getAllDogs() : Flow<List<Dog>>
}