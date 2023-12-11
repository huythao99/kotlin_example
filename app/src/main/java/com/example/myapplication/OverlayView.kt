/*
 * Copyright 2023 The TensorFlow Authors. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.myapplication

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.util.Log
import android.util.TypedValue
import android.view.View
import androidx.core.content.ContextCompat
import com.example.myapplication.models.PoseResult
import com.google.mediapipe.tasks.vision.core.RunningMode
import com.google.mediapipe.tasks.vision.poselandmarker.PoseLandmarker
import com.google.mediapipe.tasks.vision.poselandmarker.PoseLandmarkerResult
import kotlin.math.max
import kotlin.math.min

class OverlayView(context: Context?, attrs: AttributeSet?) :
    View(context, attrs) {

    private var results: PoseResult? = null
    private var pointPaint = Paint()
    private var linePaint = Paint()
    private var testPaint = Paint()

    private var scaleFactor: Float = 1f
    private var imageWidth: Int = 1
    private var imageHeight: Int = 1

    init {
        initPaints()
    }

    fun clear() {
        results = null
        pointPaint.reset()
        linePaint.reset()
        invalidate()
        initPaints()
    }

    private fun initPaints() {
        linePaint.color =
            ContextCompat.getColor(context!!, R.color.mp_color_primary)
        linePaint.strokeWidth = LANDMARK_STROKE_WIDTH
        linePaint.style = Paint.Style.STROKE

        pointPaint.color = Color.YELLOW
        pointPaint.strokeWidth = LANDMARK_STROKE_WIDTH
        pointPaint.style = Paint.Style.FILL

        testPaint.color = Color.RED
        testPaint.strokeWidth = 30F
        testPaint.style = Paint.Style.FILL
    }

    override fun draw(canvas: Canvas) {
        super.draw(canvas)
        Log.d("Draw", "width=$width, height=$height")
        val dpi = resources.displayMetrics.densityDpi // 420
        scaleFactor = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            400f / imageHeight,
            resources.displayMetrics
        )
        Log.d("Draw", "scaleFactor=$scaleFactor, imageWidth=${imageWidth}, imageHeight=$imageHeight")
        val offsetX = width / 2 - imageWidth / 2 * scaleFactor
        Log.d("Draw", "offsetX=${offsetX}")
        results?.let { poseLandmarkerResult ->
            for (i in 0..32) {
                val x = poseLandmarkerResult.normalizedPointsX[i]
                val y = poseLandmarkerResult.normalizedPointsY[i]
                val xx = x * imageWidth * scaleFactor + offsetX
                val yy = y * imageHeight * scaleFactor
                canvas.drawPoint(xx, yy, pointPaint)
                Log.d("Draw", "x,y=($xx,$yy)")
            }

            PoseLandmarker.POSE_LANDMARKS.forEach {
                val x1 = poseLandmarkerResult.normalizedPointsX[it!!.start()] * imageWidth * scaleFactor + offsetX
                val y1 = poseLandmarkerResult.normalizedPointsY[it.start()] * imageHeight * scaleFactor
                val x2 = poseLandmarkerResult.normalizedPointsX[it.end()] * imageWidth * scaleFactor + offsetX
                val y2 = poseLandmarkerResult.normalizedPointsY[it.end()] * imageHeight * scaleFactor
                canvas.drawLine(x1, y1, x2, y2, linePaint)
            }
        }
        canvas.drawPoint(0f,0f, testPaint)
        canvas.drawPoint(width*1.0f,height*1.0f, testPaint)
        canvas.drawPoint(offsetX, 0f, testPaint)
        canvas.drawPoint(width - offsetX, height*1.0f, testPaint)
    }

    fun setResults(
        poseLandmarkerResults: PoseResult,
        imageHeight: Int,
        imageWidth: Int
    ) {
        results = poseLandmarkerResults

        this.imageHeight = imageHeight
        this.imageWidth = imageWidth

        invalidate()
    }

    companion object {
        private const val LANDMARK_STROKE_WIDTH = 12F
    }
}