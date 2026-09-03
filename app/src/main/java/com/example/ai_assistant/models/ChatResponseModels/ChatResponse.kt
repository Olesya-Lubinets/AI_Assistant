package com.example.ai_assistant.models.ChatResponseModels

import com.example.ai_assistant.models.UIModels.ChatMessageUI
import com.example.ai_assistant.models.UIModels.SenderType
import com.example.ai_assistant.models.dbModels.DBMessage
import com.google.gson.annotations.SerializedName

data class ChatResponse (
    val choices: List<Choice>,
    val created: Long,
    val model: String,
    val usage: Usage?,
    @SerializedName("object")
    val objectType: String
) {
    fun toDBMessage(chatID:Long):DBMessage =  DBMessage(
        sender = SenderType.AI,
        content = choices.first().message.content?: "",
        chatID = chatID,
        createdAt = System.currentTimeMillis() / 1000
    )
}