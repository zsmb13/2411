package com.example.issue2411

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.issue2411.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val dataController: DataController = DataController()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            dataController.combinedLiveData.observe(this@MainActivity) {
                combinedCountLabel.text = it.toString()
            }

            incrementButton.setOnClickListener {
                dataController.incrementInt()
            }

            addStringButton.setOnClickListener {
                dataController.upsertString(stringEditText.text.toString())
                stringEditText.text.clear()
            }
        }
    }
}
