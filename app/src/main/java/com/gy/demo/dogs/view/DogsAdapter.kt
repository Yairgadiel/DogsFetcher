package com.gy.demo.dogs.view

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.RoundedCornersTransformation
import com.gy.demo.R
import com.gy.demo.dogs.model.Dog
import java.text.SimpleDateFormat
import java.util.*

class DogsAdapter(val dogClickedListener: IOnDogClickedListener) : ListAdapter<Dog, DogsAdapter.DogViewHolder>(DogDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DogViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.dog_item, parent, false)

        return DogViewHolder((view))
    }

    override fun onBindViewHolder(holder: DogViewHolder, position: Int) {
        holder.bind(getItem(position), dogClickedListener)
    }

    class DogViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        @SuppressLint("SimpleDateFormat")
        fun bind(dog: Dog, listener: IOnDogClickedListener) {
            itemView.findViewById<TextView>(R.id.dog_name).text = dog.name

            itemView.findViewById<TextView>(R.id.dog_day).text = SimpleDateFormat(
                "dd/MMMM/yyyy hh:mm").format(Date(dog.timestamp))

            // Loading the dog's image
            itemView.findViewById<ImageView>(R.id.dog_img).load(dog.imgPath) {
                placeholder(android.R.drawable.progress_indeterminate_horizontal)
                transformations(RoundedCornersTransformation(4f))
            }

            // Set dog clicked listener
            itemView.setOnClickListener { listener.onDogClicked(dog) }
        }
    }
}