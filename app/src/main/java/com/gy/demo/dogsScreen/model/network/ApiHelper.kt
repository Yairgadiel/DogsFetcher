package com.gy.demo.dogsScreen.model.network

interface ApiHelper {
    suspend fun fetchRandomDog() : NetworkResult<DogResponse>
}