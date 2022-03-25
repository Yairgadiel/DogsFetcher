package com.gy.demo.dogs.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gy.demo.dogs.model.Dog
import com.gy.demo.dogs.model.IDogsRepository
import com.gy.demo.dogs.model.network.DogResponse
import com.gy.demo.dogs.model.network.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DogsViewModel @Inject constructor(private val dogsRepository: IDogsRepository) : ViewModel() {

    var featuredDog: MutableLiveData<Dog> = MutableLiveData()

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

    fun setFeaturedDog(uid: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            dogsRepository.getDogById(uid).collect { featuredDog.postValue(it) }
        }
    }
}