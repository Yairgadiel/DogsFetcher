package com.gy.demo.dogsScreen.viewModel

import androidx.lifecycle.ViewModel
import com.gy.demo.dogsScreen.model.IDogsRepository
import com.gy.demo.dogsScreen.model.network.DogResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DogsViewModel @Inject constructor(private val dogsRepository: IDogsRepository) : ViewModel() {

    suspend fun fetchDog() : DogResponse {
        return dogsRepository.fetchNewDog()
    }

    fun getDogs() = dogsRepository.getAllDogs()
}