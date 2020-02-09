package com.example.hmwillywonka

import android.widget.ImageView

object Common {
    fun getIconGender(strGender: String, imgGender: ImageView)
    {
        if (strGender.equals("F")) {
            imgGender.setImageResource(R.drawable.ic_female)
        }
        else {
            imgGender.setImageResource(R.drawable.ic_male)
        }
    }
}