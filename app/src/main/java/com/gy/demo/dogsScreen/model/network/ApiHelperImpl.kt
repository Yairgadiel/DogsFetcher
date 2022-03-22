package com.gy.demo.dogsScreen.model.network

import javax.inject.Inject

class ApiHelperImpl @Inject constructor(private val apiService: ApiService) : ApiHelper {
    override suspend fun fetchRandomDog() = apiService.getRandomDog()
}