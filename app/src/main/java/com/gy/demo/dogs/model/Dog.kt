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

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Dog

        if (uid != other.uid) return false
        if (imgPath != other.imgPath) return false
        if (name != other.name) return false
        if (timestamp != other.timestamp) return false

        return true
    }

    override fun hashCode(): Int {
        var result = uid
        result = 31 * result + imgPath.hashCode()
        result = 31 * result + name.hashCode()
        result = 31 * result + timestamp.hashCode()
        return result
    }


}
