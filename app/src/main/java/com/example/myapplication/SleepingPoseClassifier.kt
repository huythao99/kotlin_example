package com.example.myapplication

import android.util.Log
import com.example.myapplication.ml.Model
import com.example.myapplication.models.PoseResult
import com.example.myapplication.models.SleepingPoseResult
import com.google.mediapipe.tasks.vision.poselandmarker.PoseLandmarker
import kotlinx.coroutines.flow.combineTransform
import org.tensorflow.lite.DataType
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer
import java.nio.ByteBuffer
import java.nio.ByteOrder

class SleepingPoseClassifier(val context: ScanActivity) {
    private var model: Model = Model.newInstance(context)

    fun classify(poseResult: PoseResult): SleepingPoseResult {
        // Define the size of the byte buffer based on your data.
        val bufferSize = 1 * 25 * 4 * 3 // Assuming each float is 4 bytes (FLOAT32) and the shape is [1, 25, 3]

        // Create a ByteBuffer
        val byteBuffer = ByteBuffer.allocateDirect(bufferSize)
        byteBuffer.order(ByteOrder.nativeOrder()) // Set the byte order (Little Endian or Big Endian) as required

        val floatData = FloatArray(1*25*3)
        for ((i, ind) in INDICES.withIndex()) {
            floatData[i * 3] = poseResult.worldPointsX[ind] // x value
            floatData[i * 3 + 1] = poseResult.worldPointsY[ind] // y value
            floatData[i * 3 + 2] = poseResult.worldPointsZ[ind] // z value
        }

        // Load data into the ByteBuffer
        byteBuffer.asFloatBuffer().put(floatData) // Put the float data into the byte buffer

        // Creates inputs for reference.
        val inputFeature0 = TensorBuffer.createFixedSize(intArrayOf(1, 25, 3), DataType.FLOAT32)
        inputFeature0.loadBuffer(byteBuffer)

        // Runs model inference and gets result.
        val outputs = model.process(inputFeature0)
        val outputFeature0 = outputs.outputFeature0AsTensorBuffer
        val prob = outputFeature0.floatArray
        var maxInd = 0
        for (i in 0..5) {
            if (prob[i] > prob[maxInd]) {
                maxInd = i
            }
        }
        val ret = SleepingPoseResult(context)
        ret.setClass(maxInd)
        Log.d("SleepingPoseClassifier", "MaxInd=$maxInd")
        return ret
    }

    fun clearModel() {
        model.close()
    }

    companion object {
        // Interesting key-points
        val INDICES = listOf<Int>(0,7,8,9,10,11,12,13,14,15,16,17,18,19,20,23,24,25,26,27,28,29,30,31,32)
    }
}
