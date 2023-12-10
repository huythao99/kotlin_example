package com.example.myapplication

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivityPhysicalExerciseBinding

class PhysicalExerciseActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPhysicalExerciseBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityPhysicalExerciseBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.exerciseBtn.setOnClickListener {
            openYoutubeLink(linkId)
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

    companion object {
        const val linkId = "OhqtsoT7Cv8"
    }
}