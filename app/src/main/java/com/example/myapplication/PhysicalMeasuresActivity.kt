package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivityPhysicalMeasuresBinding

class PhysicalMeasuresActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPhysicalMeasuresBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityPhysicalMeasuresBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.completeBtn.setOnClickListener {
            navigateToComplete()
        }

    }

    private fun navigateToComplete() {
        val intent = Intent(this, CompleteActivity::class.java);
        startActivity(intent)
    }
}