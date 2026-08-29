package com.example.ai_assistant.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.ai_assistant.models.dbModels.DBChat
import com.example.ai_assistant.models.dbModels.DBMessage

@Database(entities = [DBChat::class,DBMessage::class], version = 1,
    exportSchema = false)
abstract class AppDatabase():RoomDatabase() {
    abstract fun dbMessageDAO(): DBMessageDAO
    abstract fun dbChatDAO():DBChatDAO
}
