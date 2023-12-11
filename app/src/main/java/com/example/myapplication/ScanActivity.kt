package com.example.myapplication

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri
import com.example.myapplication.databinding.ActivityScanBinding
import com.example.myapplication.models.ImageResult
import com.example.myapplication.models.PoseResult
import java.util.concurrent.Executors
import java.util.concurrent.ScheduledExecutorService

class ScanActivity : AppCompatActivity() {

    private lateinit var binding: ActivityScanBinding
    private lateinit var images: Array<String>;

    /** Blocking ML operations are performed using this executor */
    private lateinit var backgroundExecutor: ScheduledExecutorService
    private lateinit var poseLandmarkerHelper: PoseLandmarkerHelper

    private lateinit var results: Array<ImageResult?>
    private var jobRemain: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("Scan activity", "onCreate")
        binding = ActivityScanBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // TODO: Check SDK version
        images = intent.extras?.getStringArray("uris") as Array<String>
        Log.d("PARAMS2", images[0]);

        results = arrayOfNulls(images.size)
        jobRemain = images.size
        for ((index, image) in images.withIndex()) {
            val uri = image.toUri()
            runDetectionOnImage(index, uri)
        }

        binding.resultBtn.setOnClickListener{
            showResult(images);
        }
    }

    private fun showResult(images: Array<String>) {
        Log.d("IMAGES", images[0])
        val intent = Intent(this, ResultActivity::class.java);
        Log.d("PARAMs", results[0].toString())
        intent.putExtra("results", results)
        startActivity(intent)
    }

    private fun updateUIscanDone() {
        binding.loadingContent.visibility = View.INVISIBLE;
        binding.resultContent.visibility = View.VISIBLE;
    }

    // Load and display the image.
    private fun runDetectionOnImage(i: Int, uri: Uri) {
        Log.d("runDetectionOnImage", "i=${i}")
        backgroundExecutor = Executors.newSingleThreadScheduledExecutor()

        val source = ImageDecoder.createSource(contentResolver, uri)
        ImageDecoder.decodeBitmap(source)
            .copy(Bitmap.Config.ARGB_8888, true)
            ?.let { bitmap ->
                // Run pose landmarker on the input image
                backgroundExecutor.execute {
                    poseLandmarkerHelper = PoseLandmarkerHelper(context = this)
                    poseLandmarkerHelper.detectImage(bitmap)?.let { result ->
                        results[i] = ImageResult(
                            uri.toString(),
                            "posture",
                            "advantage",
                            "weakness",
                            PoseResult(result.results[0]),
                            bitmap.width,
                            bitmap.height
                            )
                    } ?: run { Log.e("ScanActivity", "Error running pose landmarker.") }
                    poseLandmarkerHelper.clearPoseLandmarker()

                    jobRemain--;
                    if (jobRemain == 0) {
                        runOnUiThread{updateUIscanDone()}
                    }
                }
            }
    }
}
