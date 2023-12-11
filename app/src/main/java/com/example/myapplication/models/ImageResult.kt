package com.example.myapplication.models

import android.os.Parcel
import android.os.Parcelable

class ImageResult(
    val uri: String,
    val posture: String,
    val advantage: String,
    val weakness: String,
    val result: PoseResult,
    val width: Int,
    val height: Int
) : Parcelable {
    private constructor(parcel: Parcel) : this(
        uri = parcel.readString()!!,
        posture = parcel.readString()!!,
        advantage = parcel.readString()!!,
        weakness = parcel.readString()!!,
        result = parcel.readParcelable(PoseResult::class.java.classLoader)!!,
        width = parcel.readInt(),
        height = parcel.readInt()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(uri)
        parcel.writeString(posture)
        parcel.writeString(advantage)
        parcel.writeString(weakness)
        parcel.writeParcelable(result, flags)
        parcel.writeInt(width)
        parcel.writeInt(height)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ImageResult> {
        override fun createFromParcel(parcel: Parcel): ImageResult {
            return ImageResult(parcel)
        }

        override fun newArray(size: Int): Array<ImageResult?> {
            return arrayOfNulls(size)
        }
    }

}