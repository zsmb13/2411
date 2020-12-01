package com.example.issue2411

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.issue2411.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val dataController: DataController = DataController(Dispatchers.Main)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            // Shouldn't use GlobalScope in real app
            GlobalScope.launch(Dispatchers.Main) {
                dataController.combinedFlow.collectLatest {
                    combinedCountLabel.text = it.toString()
                }
            }

            incrementButtonA.setOnClickListener {
                dataController.incrementA()
            }
            incrementButtonB.setOnClickListener {
                dataController.incrementB()
            }
        }
    }
}
