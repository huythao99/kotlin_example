package com.example.myapplication

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.adapter.ImageResultAdapter
import com.example.myapplication.databinding.ActivityResultBinding
import com.example.myapplication.models.ImageResult

class ResultActivity : AppCompatActivity() {

    private lateinit var binding: ActivityResultBinding
    private lateinit var results: Array<ImageResult>

    private lateinit var resultAdapter: ImageResultAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            results = intent.extras?.getParcelableArray("results", ImageResult::class.java) as Array<ImageResult>
            Log.d("PARAMS", results.size.toString());

        } else {
            results = intent.getParcelableArrayExtra("results")!!.map{
                it as ImageResult
            }!!.toTypedArray()
            Log.d("PARAMS_URI", results[0].uri);
        }
        resultAdapter = ImageResultAdapter(results) {
            onShowExercise(results[0].classIdx)
        };
        binding.listView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.listView.adapter = resultAdapter;
    }

    private fun onShowExercise(classIdx: Int) {
        val intent = Intent(this, ExerciseActivity::class.java);
        intent.putExtra("classIdx", classIdx)
        startActivity(intent)
    }
}