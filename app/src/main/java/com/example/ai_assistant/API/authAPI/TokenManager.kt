package com.example.ai_assistant.API.authAPI

import android.util.Log
import dagger.Provides
import kotlinx.coroutines.runBlocking
import javax.inject.Inject
import javax.inject.Singleton

@Singleton

class TokenManager @Inject constructor(val authAPIService: AuthAPIService) {

    private var token:String? = null
    private var expiresAt : Long = 0


     fun getToken(): String? {
        return token
    }

    suspend fun fetchTokenIfNeeded() {
        if (token == null || checkIfTokenExpired()) {
            val  tokenResponse = authAPIService.getToken()
            saveToken(tokenResponse.access_token,tokenResponse.expires_at)
            Log.d("Token Manager", "Token has been updated")
        }
    }

    private fun saveToken(newToken:String, newExpiresAt:Long) {
        Log.d("Token manager","token: $newToken, expires: $newExpiresAt")
        token = newToken
        expiresAt = newExpiresAt
    }

    private fun checkIfTokenExpired():Boolean =  System.currentTimeMillis() >= expiresAt
}