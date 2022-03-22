package com.gy.demo.dogsScreen.model

import com.gy.demo.dogsScreen.model.local.DogsDao
import com.gy.demo.dogsScreen.model.network.ApiHelper
import com.gy.demo.dogsScreen.model.network.DogResponse
import javax.inject.Inject

class DogsRepositoryImpl @Inject constructor(
    private val dogsRemoteDataSource: ApiHelper,
    private val dogsDao: DogsDao) : IDogsRepository {

    override suspend fun fetchNewDog() : DogResponse {
        val dogRes = dogsRemoteDataSource.fetchRandomDog()

        if (dogRes.status == "success") {
            val fetchedDog = Dog(dogRes)

            // ROOM calls are "main-safe"
            dogsDao.insertDog(dog = fetchedDog)
        }

        return dogRes
    }

    override fun getAllDogs() = dogsDao.getAllDogs()
}