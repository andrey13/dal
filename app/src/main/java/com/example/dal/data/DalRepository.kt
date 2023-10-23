package com.example.dal.data

import kotlinx.coroutines.flow.Flow
import com.example.dal.data.entities.Proverb
import javax.inject.Inject

class DalRepository @Inject constructor(private val dalDao: DalDao) {

    fun getAllProverbs(): Flow<List<Proverb>> {
        return dalDao.getAllProverbs()
    }
}