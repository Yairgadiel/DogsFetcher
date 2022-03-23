package com.gy.demo.dogs.view

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gy.demo.R
import com.gy.demo.dogs.model.network.NetworkResult
import com.gy.demo.dogs.viewModel.DogsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DogsFragment : Fragment(R.layout.dogs_fragment) {
    private val dogsViewModel: DogsViewModel by activityViewModels()
    private lateinit var loader: ProgressBar
    private lateinit var dogsRV: RecyclerView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loader = view.findViewById(R.id.loader)
        dogsRV = view.findViewById(R.id.dogs)

        // Set the dogs RV
        initRV()

        // Handle fetch
        val fetchBtn = view.findViewById<Button>(R.id.fetch_btn)
        fetchBtn.setOnClickListener {
            lifecycleScope.launch(Dispatchers.Main) {
                loader.visibility = View.VISIBLE

                // Retrofit calls are "main-safe"
                val dogRes = dogsViewModel.fetchDog()

                when(dogRes) {
                    is NetworkResult.Success -> {
                        Toast.makeText(requireContext(), "Dog Fetched!" , Toast.LENGTH_SHORT).show()
                    }
                    is NetworkResult.Error -> {
                        Log.e("Main", dogRes.message ?: "Error")
                        Toast.makeText(requireContext(), "Error" , Toast.LENGTH_SHORT).show()
                    }
                    is NetworkResult.Loading -> {
                        // Not currently in use
                    }
                }

                loader.visibility = View.INVISIBLE
            }
        }
    }

    /**
     * This method initializes the dogs RV and handles updating it
     */
    private fun initRV() {
        val dogsAdapter = DogsAdapter()
        dogsRV.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = dogsAdapter
        }

        // As long as the activity is alive, update the dogs adapter when the dogs are changed
        lifecycleScope.launch(Dispatchers.Main) {
            dogsViewModel.getDogs().collect {
                dogsAdapter.submitList(it)
            }
        }
    }
}