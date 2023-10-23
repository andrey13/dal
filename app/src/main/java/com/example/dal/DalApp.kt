package com.example.dal

import android.app.Application
import android.util.Log
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class DalApp : Application() {

    init {
        Log.i("--==>", "DalApplication: init -------------------------------------------")
    }
}