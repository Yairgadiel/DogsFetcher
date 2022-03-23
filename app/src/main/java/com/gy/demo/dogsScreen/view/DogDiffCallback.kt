package com.gy.demo.dogsScreen.view

import androidx.recyclerview.widget.DiffUtil
import com.gy.demo.dogsScreen.model.Dog

object DogDiffCallback : DiffUtil.ItemCallback<Dog>() {
    override fun areItemsTheSame(oldItem: Dog, newItem: Dog): Boolean {
        return oldItem.uid == newItem.uid
    }

    override fun areContentsTheSame(oldItem: Dog, newItem: Dog): Boolean {
        return oldItem.timestamp == newItem.timestamp && oldItem.imgPath == newItem.imgPath
    }
}