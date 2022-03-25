package com.gy.demo.dogs.view

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import coil.load
import com.gy.demo.R
import com.gy.demo.dogs.viewModel.DogsViewModel

class DogProfileFragment : Fragment(R.layout.dog_profile_fragment) {
    private val dogsViewModel: DogsViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dogsViewModel.featuredDog.observe(viewLifecycleOwner, Observer {
            view.findViewById<ImageView>(R.id.dog_profile_image).load(it?.imgPath) {
                placeholder(android.R.drawable.progress_indeterminate_horizontal)
            }

            view.findViewById<TextView>(R.id.dog_profile_name).text = it?.name
        })
    }
}