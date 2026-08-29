package com.example.ai_assistant.models.dbModels

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "chats")
data class DBChat (
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val title:String,
    val createdAt:Long
)