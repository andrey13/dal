package com.example.dal

import android.app.Application
import android.util.Log
import com.example.dal.data.DalDataBase
import com.example.dal.data.DalRepository

class DalApp : Application() {
    private val database by lazy { DalDataBase.getDatabase(this) }
    val repo by lazy { DalRepository(database.dalDao()) }

    init {
        Log.i("--==>", "DalApplication: init -------------------------------------------")
        //tabText = resources.getStringArray(R.array.tab_array)
    }
}