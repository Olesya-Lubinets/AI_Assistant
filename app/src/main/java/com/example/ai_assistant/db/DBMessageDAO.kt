package com.example.ai_assistant.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.ai_assistant.models.dbModels.DBMessage
import kotlinx.coroutines.flow.Flow

@Dao
interface DBMessageDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(user: DBMessage)

    @Delete
    suspend fun delete(dbChat: DBMessage)

    @Query("SELECT * FROM messages")
    suspend fun getAll(): List<DBMessage>

    @Query("SELECT * FROM messages WHERE id = :id")
    suspend fun getById(id: Long): DBMessage?

    @Query("SELECT * FROM messages WHERE chatID=:chatID")
    fun getMessagesByChatID(chatID:Long): Flow<List<DBMessage>>
}
