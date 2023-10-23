package com.example.dal.data

import androidx.room.*
import com.example.dal.data.entities.*

@Database(
    entities = [
        (Proverb::class),
    ],
    version = 1,
//    autoMigrations = [
//        AutoMigration(
//            from = 1,
//            to = 2,
//            spec = Migration1To2::class
//        ) ],
    exportSchema = true
)
abstract class DalDataBase : RoomDatabase() {

    abstract fun dalDao(): DalDao


}