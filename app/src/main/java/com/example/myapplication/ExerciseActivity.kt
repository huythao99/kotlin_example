package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivityExerciseBinding
import com.example.myapplication.models.ImageResult

class ExerciseActivity : AppCompatActivity() {

    private lateinit var binding: ActivityExerciseBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val classIdx = intent.extras?.getInt("classIdx")!!

        binding = ActivityExerciseBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.physicalExerciseBtn.setOnClickListener {
            onShowPhysicalExercise(classIdx)
        }

        binding.physicalMeasuresBtn.setOnClickListener {
            onShowPhysicalMeasure(classIdx)
        }
    }

    private fun onShowPhysicalExercise(classIdx: Int) {
        val intent = Intent(this, PhysicalExerciseActivity::class.java);
        intent.putExtra("classIdx", classIdx)
        startActivity(intent)
    }

    private fun onShowPhysicalMeasure(classIdx: Int) {
        val intent = Intent(this, PhysicalMeasuresActivity::class.java);
        intent.putExtra("classIdx", classIdx)
        startActivity(intent)
    }
}