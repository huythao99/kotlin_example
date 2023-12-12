package com.example.myapplication.models

import android.os.Parcel
import android.os.Parcelable
import com.google.mediapipe.tasks.vision.poselandmarker.PoseLandmarkerResult

class PoseResult() : Parcelable {
    var normalizedPointsX: FloatArray = FloatArray(33)
    var normalizedPointsY: FloatArray = FloatArray(33)
    var worldPointsX: FloatArray = FloatArray(33)
    var worldPointsY: FloatArray = FloatArray(33)
    var worldPointsZ: FloatArray = FloatArray(33)

    constructor (poseLandmarkerResult: PoseLandmarkerResult) : this() {
        // Get normalize points
        for(landmark in poseLandmarkerResult.landmarks()) {
            for((i, normalizedLandmark) in landmark.withIndex()) {
                normalizedPointsX[i] = normalizedLandmark.x()
                normalizedPointsY[i] = normalizedLandmark.y()
            }
            break // only care first landmark (mediapipe may return many landmarks in one image)
        }

        // Get world points
        for(landmark in poseLandmarkerResult.worldLandmarks()) {
            for((i, worldLandmark) in landmark.withIndex()) {
                worldPointsX[i] = worldLandmark.x()
                worldPointsY[i] = worldLandmark.y()
                worldPointsZ[i] = worldLandmark.z()
            }
            break // only care first landmark (mediapipe may return many landmarks in one image)
        }
    }

    constructor(parcel: Parcel) : this() {
        parcel.readFloatArray(normalizedPointsX)
        parcel.readFloatArray(normalizedPointsY)
        parcel.readFloatArray(worldPointsX)
        parcel.readFloatArray(worldPointsY)
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeFloatArray(normalizedPointsX)
        parcel.writeFloatArray(normalizedPointsY)
        parcel.writeFloatArray(worldPointsX)
        parcel.writeFloatArray(worldPointsY)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<PoseResult> {
        override fun createFromParcel(parcel: Parcel): PoseResult {
            return PoseResult(parcel)
        }

        override fun newArray(size: Int): Array<PoseResult?> {
            return arrayOfNulls(size)
        }
    }
}