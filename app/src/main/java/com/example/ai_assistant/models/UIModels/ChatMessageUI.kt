package com.example.ai_assistant.models.UIModels

data class ChatMessageUI (
    val sender: SenderType,
    val text:String
)


enum class SenderType {
 USER, AI
}
