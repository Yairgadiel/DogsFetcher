package com.gy.demo.dogsScreen.model.network

/**
 * Interface representing the functionality required by the remote data source
 */
interface ApiHelper {
    suspend fun fetchRandomDog() : NetworkResult<DogResponse>
}