package com.gy.demo.dogsScreen.model.network

import retrofit2.Response
import retrofit2.http.GET

/**
 * The API communicating with the remote source
 */
interface ApiService {
    @GET("random")
    suspend fun getRandomDog() : Response<DogResponse>
}