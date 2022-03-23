package com.gy.demo.dogs.model

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.gy.demo.dogs.model.network.DogResponse
import java.util.*

/**
 * A dog entity as saved locally
 */
@Entity
class Dog(
    @PrimaryKey(autoGenerate = true)
    val uid: Int,
    val imgPath: String,
    val name: String,
    val timestamp: Long,
) {
    @Ignore
    constructor(dogRes: DogResponse) : this(
        0,
        dogRes.imgPath,
        dogRes.imgPath.split("breeds/")[1].split('/')[0],
        Date().time)
}
