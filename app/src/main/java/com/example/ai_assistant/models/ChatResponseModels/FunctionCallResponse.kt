package com.example.ai_assistant.models.ChatResponseModels

data class FunctionCallResponse (
    val name: String,
    val arguments: String
)
