package com.gy.demo.dogsScreen.model.network

import com.squareup.moshi.Json

/**
 * A dog response as received by the remote
 */
data class DogResponse(
    @Json(name = "status")
    val status: String,

    @Json(name = "message")
    val imgPath: String
)
