package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivityPhysicalMeasuresBinding
import com.example.myapplication.models.SleepingPoseResult

class PhysicalMeasuresActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPhysicalMeasuresBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityPhysicalMeasuresBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val classIdx = intent.extras?.getInt("classIdx")!!
        val sleepingPoseResult = SleepingPoseResult(this)
        sleepingPoseResult.setClass(classIdx)
        binding.completeBtn.setOnClickListener {
            navigateToComplete()
        }
        binding.steps.text = sleepingPoseResult.physicalMeasure
    }

    private fun navigateToComplete() {
        val intent = Intent(this, CompleteActivity::class.java);
        startActivity(intent)
    }
}