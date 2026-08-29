package com.example.ai_assistant.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.ai_assistant.models.dbModels.DBChat

@Dao
interface DBChatDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(dbChat: DBChat):Long

    @Delete
    suspend fun delete(dbChat: DBChat)

    @Query("SELECT * FROM chats")
    suspend fun getAll(): List<DBChat>

    @Query("SELECT * FROM chats WHERE id = :id")
    suspend fun getById(id: Long): DBChat?
}
