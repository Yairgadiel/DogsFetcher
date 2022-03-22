package com.gy.demo.dogsScreen.model.network

import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("random")
    suspend fun getRandomDog() : Response<DogResponse>
}