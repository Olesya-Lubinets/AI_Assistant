package com.example.ai_assistant.di

import android.content.Context
import androidx.room.Room

import com.example.ai_assistant.db.AppDatabase
import com.example.ai_assistant.db.DBChatDAO
import com.example.ai_assistant.db.DBMessageDAO
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DBModule {

    @Provides
    @Singleton
    fun provideDataBase(@ApplicationContext ctx: Context): AppDatabase {
        return Room.databaseBuilder(
            ctx.applicationContext,
            AppDatabase::class.java,
            "aiAssistant_database"
        )
            .fallbackToDestructiveMigration(true)
            .build()
    }

    @Provides
    @Singleton
    fun providesDBChatDAO(dataBase:AppDatabase):DBChatDAO = dataBase.dbChatDAO()

    @Provides
    @Singleton
    fun providesDBMessageDAO(dataBase:AppDatabase):DBMessageDAO =  dataBase.dbMessageDAO()
}