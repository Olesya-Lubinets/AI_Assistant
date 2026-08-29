package com.example.ai_assistant

import com.example.ai_assistant.db.DBMessageDAO
import com.example.ai_assistant.models.dbModels.DBMessage
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MessageRepository @Inject constructor(val dao: DBMessageDAO) {

    suspend fun addMessage(message: DBMessage) = dao.insert(message)

    suspend fun deleteMessage(message: DBMessage) = dao.delete(message)

    suspend fun getMessageById(id: Long):DBMessage? = dao.getById(id)

    fun getAllMessagesByChatID(id: Long): Flow<List<DBMessage>> = dao.getMessagesByChatID(id)

}