package com.gy.demo.dogs.view

import com.gy.demo.dogs.model.Dog

interface IOnDogClickedListener {
    fun onDogClicked(dog: Dog)
}