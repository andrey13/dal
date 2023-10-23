package com.example.dal.di

import android.content.Context
import androidx.room.Room
import com.example.dal.data.DalDao
import com.example.dal.data.DalDataBase
import com.example.dal.data.DalRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Provides
    @Singleton
    fun provideDalDataBase(@ApplicationContext context: Context) : DalDataBase =
        Room.databaseBuilder(
            context.applicationContext,
            DalDataBase::class.java,
            "dal.db"
        ).createFromAsset("dal.db").build()

    @Provides
    @Singleton
    fun provideDalDao(dalDataBase: DalDataBase) : DalDao =
        dalDataBase.dalDao()

    @Provides
    @Singleton
    fun provideDalRepository(dalDao: DalDao) : DalRepository =
        DalRepository(dalDao)

}