package com.gy.demo.dogs.model

import com.gy.demo.dogs.model.network.DogResponse
import com.gy.demo.dogs.model.network.NetworkResult
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