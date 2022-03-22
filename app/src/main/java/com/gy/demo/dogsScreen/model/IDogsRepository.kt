package com.gy.demo.dogsScreen.model

import com.gy.demo.dogsScreen.model.network.DogResponse
import com.gy.demo.dogsScreen.model.network.NetworkResult
import kotlinx.coroutines.flow.Flow

interface IDogsRepository {
    suspend fun fetchNewDog() : NetworkResult<DogResponse>

    fun getAllDogs() : Flow<List<Dog>>
}