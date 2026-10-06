package com.example.ai_assistant

import androidx.paging.Pager
import com.example.ai_assistant.db.DBChatDAO
import com.example.ai_assistant.models.dbModels.DBChat
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import androidx.paging.PagingConfig
import androidx.paging.PagingData


class ChatRepository @Inject constructor(val dao: DBChatDAO) {

    suspend fun addChat(dbChat: DBChat):Long = dao.insert(dbChat)

    suspend fun deleteChat(dbChat: DBChat) = dao.delete(dbChat)

    fun getAllChats(): Flow<PagingData<DBChat>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                prefetchDistance = 5),
            pagingSourceFactory = {dao.getAll()}).flow
    }

    suspend fun getChatByID(id: Long): DBChat? = dao.getById(id)

    suspend fun updateChatTitle(id:Long,title:String) = dao.updateTitle(id,title)

    fun searchChats(query:String): Flow<PagingData<DBChat>> {
        return Pager(
            config = PagingConfig(pageSize = 15),
            pagingSourceFactory = { dao.searchChats(query)}
        ).flow
    }
}