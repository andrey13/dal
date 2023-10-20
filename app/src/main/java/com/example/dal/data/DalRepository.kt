package com.example.dal.data

import android.util.Log
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import com.example.dal.data.entities.Proverb

class DalRepository(private val dalDao: DalDao) {

    fun getAllProverbs(): Flow<List<Proverb>> {
        val res1 = dalDao.getAllProverbs()
        Log.i("--==>", "res1 = $res1")
        return res1
    }
//    fun getAllF(): Flow<List<Proverb>> {
//        val ll = List(10) {index -> Proverb(index, "text $index") }
//        Log.i("--==>", "data2")
//        val ff = flow { emit(ll) }
//        Log.i("--==>", "data3")
//        return ff
//    }
}