package com.gy.demo.dogsScreen.model

import com.gy.demo.dogsScreen.model.local.DogsDao
import com.gy.demo.dogsScreen.model.network.ApiHelper
import com.gy.demo.dogsScreen.model.network.DogResponse
import com.gy.demo.dogsScreen.model.network.NetworkResult
import javax.inject.Inject

class DogsRepositoryImpl @Inject constructor(
    private val dogsRemoteDataSource: ApiHelper,
    private val dogsDao: DogsDao) : IDogsRepository {

    override suspend fun fetchNewDog() : NetworkResult<DogResponse> {
        val dogNetworkRes : NetworkResult<DogResponse> = dogsRemoteDataSource.fetchRandomDog()

        if (dogNetworkRes is NetworkResult.Success) {
            dogNetworkRes.data?.let {
                val fetchedDog = Dog(it)

                // ROOM calls are "main-safe"
                dogsDao.insertDog(fetchedDog)
            } ?: print("Should not happen!")
        }

        return dogNetworkRes
    }

    override fun getAllDogs() = dogsDao.getAllDogs()
}