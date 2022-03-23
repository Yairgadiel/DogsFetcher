package com.gy.demo.dogsScreen.viewModel

import androidx.lifecycle.ViewModel
import com.gy.demo.dogsScreen.model.Dog
import com.gy.demo.dogsScreen.model.IDogsRepository
import com.gy.demo.dogsScreen.model.network.DogResponse
import com.gy.demo.dogsScreen.model.network.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class DogsViewModel @Inject constructor(private val dogsRepository: IDogsRepository) : ViewModel() {

    /**
     * Method attempts to fetch a new Dog
     * @return NetworkResult representing the dog response
     */
    suspend fun fetchDog() : NetworkResult<DogResponse> {
        return dogsRepository.fetchNewDog()
    }

    /**
     * Method returns flow of Dogs
     */
    fun getDogs() : Flow<List<Dog>> = dogsRepository.getAllDogs()
}