package com.gy.demo.dogs.model.network

/**
 * Interface representing the functionality required by the remote data source
 */
interface ApiHelper {
    suspend fun fetchRandomDog() : NetworkResult<DogResponse>
}