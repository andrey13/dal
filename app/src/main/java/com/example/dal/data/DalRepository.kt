package com.example.dal.data

import kotlinx.coroutines.flow.Flow
import com.example.dal.data.entities.Proverb

class DalRepository(private val dalDao: DalDao) {

    fun getAllProverbs(): Flow<List<Proverb>> {
        val res1 = dalDao.getAllProverbs()
        return res1
    }

//    suspend fun getAll(): List<Proverb> {
//        return dalDao.getAll()
//    }

//    fun getAllF(): Flow<List<Proverb>> {
//        val ll = List(10) {index -> Proverb(index, "text $index") }
//        Log.i("--==>", "data2")
//        val ff = flow { emit(ll) }
//        Log.i("--==>", "data3")
//        return ff
//    }
}