package com.example.ai_assistant.models.AuthModels

data class AuthTokenResponse (
    val access_token:String,
    val expires_at:Long
)