package com.example.ai_assistant

import com.example.ai_assistant.db.DBChatDAO
import com.example.ai_assistant.models.dbModels.DBChat
import javax.inject.Inject

class ChatRepository @Inject constructor(val dao: DBChatDAO) {

    suspend fun addChat(dbChat: DBChat):Long = dao.insert(dbChat)

    suspend fun deleteChat(dbChat: DBChat) = dao.delete(dbChat)

    suspend fun getAllChats(): List<DBChat> =  dao.getAll()

    suspend fun getChatByID(id: Long): DBChat? = dao.getById(id)
}