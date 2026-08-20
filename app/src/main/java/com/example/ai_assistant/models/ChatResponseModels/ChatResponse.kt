package com.example.ai_assistant.models.ChatResponseModels

import com.google.gson.annotations.SerializedName

data class ChatResponse (
    val choices: List<Choice>,
    val created: Long,
    val model: String,
    val usage: Usage?,
    @SerializedName("object")
    val objectType: String
)