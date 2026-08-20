package com.example.ai_assistant.API.chatAPI

import com.example.ai_assistant.models.ChatRequestModels.ChatRequest
import com.example.ai_assistant.models.ChatResponseModels.ChatResponse
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST
import java.util.UUID

interface ChatAPIService {

    @Headers("Accept: application/json")
    @POST("v1/chat/completions")
    suspend fun getChatResponse(
        @Header("X-Client-ID")  xClientID:String,
        @Header("User-Agent")  userAgent:String,
        @Body chatRequest: ChatRequest
    ): ChatResponse
}