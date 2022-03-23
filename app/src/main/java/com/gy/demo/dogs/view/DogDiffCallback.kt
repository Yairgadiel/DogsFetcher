package com.gy.demo.dogs.view

import androidx.recyclerview.widget.DiffUtil
import com.gy.demo.dogs.model.Dog

object DogDiffCallback : DiffUtil.ItemCallback<Dog>() {
    override fun areItemsTheSame(oldItem: Dog, newItem: Dog): Boolean {
        return oldItem.uid == newItem.uid
    }

    override fun areContentsTheSame(oldItem: Dog, newItem: Dog): Boolean {
        return oldItem.timestamp == newItem.timestamp && oldItem.imgPath == newItem.imgPath
    }
}