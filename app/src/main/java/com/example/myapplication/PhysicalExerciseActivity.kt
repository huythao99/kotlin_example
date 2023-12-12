package com.example.myapplication

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivityPhysicalExerciseBinding
import com.example.myapplication.models.SleepingPoseResult

class PhysicalExerciseActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPhysicalExerciseBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityPhysicalExerciseBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val classIdx = intent.extras?.getInt("classIdx")!!
        val sleepingPoseResult = SleepingPoseResult(this)
        sleepingPoseResult.setClass(classIdx)
        binding.exerciseBtn.setOnClickListener {
            openYoutubeLink(sleepingPoseResult.youtubeID)
        }

        binding.completeBtn.setOnClickListener {
            navigateToComplete()
        }
    }

    private fun openYoutubeLink(youtubeID: String) {
        val intentApp = Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:$youtubeID"))
        val intentBrowser = Intent(Intent.ACTION_VIEW, Uri.parse("http://www.youtube.com/watch?v=$youtubeID"))
        try {
            this.startActivity(intentApp)
        } catch (ex: ActivityNotFoundException) {
            this.startActivity(intentBrowser)
        }

    }

    private fun navigateToComplete() {
        val intent = Intent(this, CompleteActivity::class.java)
        startActivity(intent)
    }
}