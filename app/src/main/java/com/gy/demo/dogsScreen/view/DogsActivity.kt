package com.gy.demo.dogsScreen.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gy.demo.R
import com.gy.demo.dogsScreen.viewModel.DogsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DogsActivity : AppCompatActivity() {
    private val dogsViewModel: DogsViewModel by viewModels()

    private lateinit var loader: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dogs)

        loader = findViewById(R.id.loader)

        initRV()

        val fetchBtn = findViewById<Button>(R.id.fetch_btn)
        fetchBtn.setOnClickListener {
            lifecycleScope.launch(Dispatchers.Main) {
                loader.visibility = View.VISIBLE

                // Retrofit calls are "main-safe"
                val res = dogsViewModel.fetchDog()

                loader.visibility = View.INVISIBLE
                Toast.makeText(this@DogsActivity, "Dog Fetch status: ${res.status}" , Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun initRV() {
        val dogsAdapter = DogsAdapter()
        val dogsRV = findViewById<RecyclerView>(R.id.dogs)
        dogsRV.apply {
            layoutManager = LinearLayoutManager(this@DogsActivity)
            adapter = dogsAdapter
        }

        lifecycleScope.launch(Dispatchers.Main) {
            dogsViewModel.getDogs().collect {
                dogsAdapter.submitList(it)
            }
        }
    }
}