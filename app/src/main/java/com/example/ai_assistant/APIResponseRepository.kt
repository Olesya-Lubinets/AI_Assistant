package com.example.ai_assistant

import com.example.ai_assistant.API.authAPI.TokenManager
import com.example.ai_assistant.API.chatAPI.ChatAPIService
import com.example.ai_assistant.models.ChatRequestModels.ChatRequest
import com.example.ai_assistant.models.ChatRequestModels.MessageRequest
import com.example.ai_assistant.models.ChatResponseModels.ChatResponse
import javax.inject.Inject

class APIResponseRepository @Inject constructor(val api: ChatAPIService, val tokenManager: TokenManager)
    {
    suspend fun getChatResponse(request:List<MessageRequest>): ChatResponse {
        tokenManager.fetchTokenIfNeeded()

        return api.getChatResponse(
            userAgent = "Some client identification",
            chatRequest = ChatRequest("GigaChat-2-Max",request),
            xClientID = "Some Client ID"
        )
    }
}