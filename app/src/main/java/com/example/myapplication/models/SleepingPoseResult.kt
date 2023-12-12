package com.example.myapplication.models

import android.content.Context
import com.example.myapplication.R

class SleepingPoseResult(val context: Context) {
    var classIdx = -1
    lateinit var classname: String
    lateinit var advantage: String
    lateinit var disadvantage: String
    lateinit var physicalMeasure: String
    lateinit var youtubeID: String

    fun setClass(classInd: Int) {
        classIdx = classInd
        when(classInd) {
            0->{
                classname = context.getString(R.string.posture_0_name)
                advantage = context.getString(R.string.posture_0_advantage)
                disadvantage = context.getString(R.string.posture_0_disadvantage)
                physicalMeasure = context.getString(R.string.posture_0_physical_measure)
                youtubeID = context.getString(R.string.posture_0_video_link)
            }
            1->{
                classname = context.getString(R.string.posture_1_name)
                advantage = context.getString(R.string.posture_1_advantage)
                disadvantage = context.getString(R.string.posture_1_disadvantage)
                physicalMeasure = context.getString(R.string.posture_1_physical_measure)
                youtubeID = context.getString(R.string.posture_1_video_link)
            }
            2->{
                classname =context.getString(R.string.posture_2_name)
                advantage = context.getString(R.string.posture_2_advantage)
                disadvantage = context.getString(R.string.posture_2_disadvantage)
                physicalMeasure = context.getString(R.string.posture_2_physical_measure)
                youtubeID = context.getString(R.string.posture_2_video_link)
            }
            3->{
                classname = context.getString(R.string.posture_3_name)
                advantage = context.getString(R.string.posture_3_advantage)
                disadvantage = context.getString(R.string.posture_3_disadvantage)
                physicalMeasure = context.getString(R.string.posture_3_physical_measure)
                youtubeID = context.getString(R.string.posture_3_video_link)
            }
            4->{
                classname = context.getString(R.string.posture_4_name)
                advantage = context.getString(R.string.posture_4_advantage)
                disadvantage = context.getString(R.string.posture_4_disadvantage)
                physicalMeasure = context.getString(R.string.posture_4_physical_measure)
                youtubeID = context.getString(R.string.posture_4_video_link)
            }
            5->{
                classname = context.getString(R.string.posture_5_name)
                advantage = context.getString(R.string.posture_5_advantage)
                disadvantage = context.getString(R.string.posture_5_disadvantage)
                physicalMeasure = context.getString(R.string.posture_5_physical_measure)
                youtubeID = context.getString(R.string.posture_5_video_link)
            }
        }
    }
}