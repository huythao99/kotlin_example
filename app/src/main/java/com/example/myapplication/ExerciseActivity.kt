package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivityExerciseBinding

class ExerciseActivity : AppCompatActivity() {

    private lateinit var binding: ActivityExerciseBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityExerciseBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.physicalExerciseBtn.setOnClickListener {
            onShowPhysicalExercise()
        }

        binding.physicalMeasuresBtn.setOnClickListener {
            onShowPhysicalMeasure()
        }
    }

    private fun onShowPhysicalExercise() {
        val intent = Intent(this, PhysicalExerciseActivity::class.java);
        startActivity(intent)
    }

    private fun onShowPhysicalMeasure() {
        val intent = Intent(this, PhysicalMeasuresActivity::class.java);
        startActivity(intent)
    }
}