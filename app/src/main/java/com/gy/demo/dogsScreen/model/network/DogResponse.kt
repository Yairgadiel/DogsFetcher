package com.gy.demo.dogsScreen.model.network

import com.squareup.moshi.Json

data class DogResponse(
    @Json(name = "status")
    val status: String,

    @Json(name = "message")
    val imgPath: String
)
