package com.gy.demo.dogsScreen.model

import com.gy.demo.dogsScreen.model.network.DogResponse
import com.gy.demo.dogsScreen.model.network.NetworkResult
import kotlinx.coroutines.flow.Flow

/**
 * Interface representing the contract which the dogs screen requires
 */
interface IDogsRepository {
    /**
     * Method fetching a new dog
     */
    suspend fun fetchNewDog() : NetworkResult<DogResponse>

    /**
     * Method retrieving all app's dogs
     */
    fun getAllDogs() : Flow<List<Dog>>
}