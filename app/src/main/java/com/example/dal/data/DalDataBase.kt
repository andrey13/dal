package com.example.dal.data

import android.content.Context
import android.util.Log
import androidx.room.*
//import androidx.room.migration.AutoMigrationSpec
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


    companion object {

//        private val MIGRATION_1_TO_2 = Migration1To2()
//        private val MIGRATION_2_TO_3 = Migration2To3()

        //@Volatile
        private var INSTANCE: DalDataBase? = null

        fun getDatabase(context: Context): DalDataBase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        DalDataBase::class.java,
                        "dal.db"
                    )
                        //.fallbackToDestructiveMigration()
//                        .addMigrations(MIGRATION_1_TO_2)
//                        .addMigrations(MIGRATION_2_TO_3)
                        .createFromAsset("dal.db")
                        .build()

                    INSTANCE = instance
                }

                return instance
            }
        }
    }
}