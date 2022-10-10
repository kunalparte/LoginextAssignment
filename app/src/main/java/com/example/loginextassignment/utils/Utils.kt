package com.example.loginextassignment.utils

import android.app.Activity
import android.util.DisplayMetrics
import com.example.loginextassignment.R

object Utils {

    fun getScreenWidth(activity: Activity): Int {
        val displayMetrics = DisplayMetrics()
        activity.windowManager.defaultDisplay.getMetrics(displayMetrics)
        val width = displayMetrics.widthPixels
        return width
    }

    fun returnnColor(rating : Float) : Int{
        if (rating >= 0f && rating < 2f){
            return R.color.rating_0_2
        }else if (rating >= 2f && rating < 4f){
            return R.color.rating_2_4
        }else{
            return R.color.rating_4_5
        }
    }

}