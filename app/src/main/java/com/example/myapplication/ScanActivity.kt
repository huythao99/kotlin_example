package com.example.myapplication

import android.content.Intent
import android.media.Image
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivityScanBinding
import com.example.myapplication.models.ImageResult

class ScanActivity : AppCompatActivity() {

    private lateinit var binding: ActivityScanBinding
    private lateinit var images: Array<String>;


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityScanBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Handler(Looper.getMainLooper()).postDelayed({
            binding.loadingContent.visibility = View.INVISIBLE;
            binding.resultContent.visibility = View.VISIBLE;
        }, 3000);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            images = intent.extras?.getParcelableArray("uris", String::class.java) as Array<String>
            Log.d("PARAMS", images.size.toString());

        } else {
            images = intent.extras?.getStringArray("uris") as Array<String>
            Log.d("PARAMS", images[0]);
        }
        binding.resultBtn.setOnClickListener{
            showResult(images);
        }
    }

    private fun showResult(images: Array<String>) {
        Log.d("IMAGES", images[0])
        val intent = Intent(this, ResultActivity::class.java);
        val results = Array<ImageResult>(images.size) {
            ImageResult(images[it], "Posture", "Advantage", "Weakness")
        }
        Log.d("PARAMs", results[0].toString())
        intent.putExtra("results", results)
        startActivity(intent)
    }
}
