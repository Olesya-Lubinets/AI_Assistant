package com.example.ai_assistant.API.chatAPI

import android.util.Log
import com.example.ai_assistant.API.authAPI.TokenManager
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject


class ChatInterceptor @Inject constructor(private val tokenManager: TokenManager) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {

        val currentToken = tokenManager.getToken() ?: throw IllegalStateException("Access token is not available")
        val request = chain
            .request().newBuilder()
            .addHeader("Authorization", "Bearer $currentToken")
            .build()
        Log.d(
            "ChatInterceptor",
            "Token is added to request. Token: "
        )
        return chain.proceed(request)
    }
}