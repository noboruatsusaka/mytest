package com.example.myapplication

import android.widget.ProgressBar

class MyApp {
    var QRResult: String? = null
    var barEnemy: ProgressBar? = null
     var dmg:Int =0

    companion object {
        private var instance : MyApp? = null

        fun  getInstance(): MyApp {
            if (instance == null)
                instance = MyApp()

            return instance!!
        }
    }

}