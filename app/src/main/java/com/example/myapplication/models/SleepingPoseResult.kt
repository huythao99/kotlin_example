package com.example.myapplication.models

import android.content.Context
import com.example.myapplication.R

class SleepingPoseResult(val context: Context) {
    lateinit var classname: String
    lateinit var advantage: String
    lateinit var disadvantage: String

    fun setClass(classInd: Int) {
        when(classInd) {
            0->{
                classname = context.getString(R.string.posture_0_name)
                advantage = context.getString(R.string.posture_0_advantage)
                disadvantage = context.getString(R.string.posture_0_disadvantage)
            }
            1->{
                classname = context.getString(R.string.posture_1_name)
                advantage = context.getString(R.string.posture_1_advantage)
                disadvantage = context.getString(R.string.posture_1_disadvantage)
            }
            2->{
                classname =context.getString(R.string.posture_2_name)
                advantage = context.getString(R.string.posture_2_advantage)
                disadvantage = context.getString(R.string.posture_2_disadvantage)
            }
            3->{
                classname = context.getString(R.string.posture_3_name)
                advantage = context.getString(R.string.posture_3_advantage)
                disadvantage = context.getString(R.string.posture_3_disadvantage)
            }
            4->{
                classname = context.getString(R.string.posture_4_name)
                advantage = context.getString(R.string.posture_4_advantage)
                disadvantage = context.getString(R.string.posture_4_disadvantage)
            }
            5->{
                classname = context.getString(R.string.posture_5_name)
                advantage = context.getString(R.string.posture_5_advantage)
                disadvantage = context.getString(R.string.posture_5_disadvantage)
            }
        }
    }
}