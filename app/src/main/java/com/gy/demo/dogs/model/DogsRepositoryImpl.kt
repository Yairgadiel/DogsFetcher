package com.gy.demo.dogs.model

import com.gy.demo.dogs.model.local.DogsDao
import com.gy.demo.dogs.model.network.ApiHelper
import com.gy.demo.dogs.model.network.DogResponse
import com.gy.demo.dogs.model.network.NetworkResult
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Class for handling dogs related data and functionality
 */
class DogsRepositoryImpl @Inject constructor(
    private val dogsRemoteDataSource: ApiHelper,
    private val dogsDao: DogsDao) : IDogsRepository {

    override suspend fun fetchNewDog() : NetworkResult<DogResponse> {
        val dogNetworkRes : NetworkResult<DogResponse> = dogsRemoteDataSource.fetchRandomDog()

        // If a dog was fetched successfully - saving it locally
        if (dogNetworkRes is NetworkResult.Success) {
            dogNetworkRes.data?.let {
                val fetchedDog = Dog(it)

                // ROOM calls are "main-safe"
                dogsDao.insertDog(fetchedDog)
            }
        }

        return dogNetworkRes
    }

    override fun getAllDogs() : Flow<List<Dog>> = dogsDao.getAllDogs()

    override fun getDogById(id: Int): Flow<Dog> = dogsDao.getDogById(id)

}