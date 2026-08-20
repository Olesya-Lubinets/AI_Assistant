package com.example.ai_assistant.models.ChatRequestModels

import com.google.gson.JsonObject

data class ExampleRequest(
    val request: String,
    val params: JsonObject
)
