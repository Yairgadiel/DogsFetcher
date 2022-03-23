package com.gy.demo.dogsScreen.model.network

import java.lang.Exception
import javax.inject.Inject

/**
 * Class for handling all remote dog-related functionality
 */
class ApiHelperImpl @Inject constructor(private val apiService: ApiService) : ApiHelper {

    override suspend fun fetchRandomDog() : NetworkResult<DogResponse> {

        try {
            // Attempt to fetch a new dog
            val dogRes = apiService.getRandomDog()

            if (dogRes.isSuccessful) {
                dogRes.body()?.let {
                    return NetworkResult.Success(it)
                }
            }

            return error("${dogRes.code()} ${dogRes.message()}")
        }
        catch (e: Exception) {
            e.printStackTrace()

            return error(e.message ?: e.toString())
        }
    }

    /**
     * Method instantiating an error NetworkResult result
     */
    private fun <T> error(errorMessage: String): NetworkResult<T> =
        NetworkResult.Error("Api call failed $errorMessage")
}