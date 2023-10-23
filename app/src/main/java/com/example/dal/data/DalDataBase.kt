package com.example.dal.data

import androidx.room.*
import com.example.dal.data.entities.*

@Database(
    entities = [
        (Proverb::class),
    ],
    version = 1,
    exportSchema = true
)
abstract class DalDataBase : RoomDatabase() {

    abstract fun dalDao(): DalDao
}