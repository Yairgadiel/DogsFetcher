package com.gy.demo.dogsScreen.view

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.RoundedCornersTransformation
import com.gy.demo.R
import com.gy.demo.dogsScreen.model.Dog
import java.text.SimpleDateFormat
import java.util.*

class DogsAdapter : ListAdapter<Dog, DogsAdapter.DogViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DogViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.dog_item, parent, false)

        return DogViewHolder((view))
    }

    override fun onBindViewHolder(holder: DogViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class DogViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        @SuppressLint("SimpleDateFormat")
        fun bind(dog: Dog) {
            itemView.findViewById<TextView>(R.id.dog_name).text = dog.name

            itemView.findViewById<TextView>(R.id.dog_day).text = SimpleDateFormat(
                "dd/MMMM/yyyy hh:mm").format(Date(dog.timestamp))
            itemView.findViewById<ImageView>(R.id.dog_img).load(dog.imgPath) {
                transformations(RoundedCornersTransformation(4f))
            }
        }
    }

    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<Dog>() {
            override fun areItemsTheSame(oldItem: Dog, newItem: Dog): Boolean {
                return oldItem.uid == newItem.uid
            }

            override fun areContentsTheSame(oldItem: Dog, newItem: Dog): Boolean {
                return oldItem.timestamp == newItem.timestamp && oldItem.imgPath == newItem.imgPath
            }
        }
    }}