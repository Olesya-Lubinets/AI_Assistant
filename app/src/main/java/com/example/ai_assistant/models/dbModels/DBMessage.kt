package com.example.ai_assistant.models.dbModels

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.example.ai_assistant.models.ChatRequestModels.AssistantMessageRequest
import com.example.ai_assistant.models.ChatRequestModels.MessageRequest
import com.example.ai_assistant.models.ChatRequestModels.UserMessageRequest
import com.example.ai_assistant.models.UIModels.SenderType


@Entity(
    tableName = "messages",
    foreignKeys = [
        ForeignKey(
            entity = DBChat::class,
            parentColumns = ["id"],
            childColumns =["chatID"],
            onDelete = ForeignKey.CASCADE,
        )
    ], indices = [Index("chatID")]
)
data class DBMessage (
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val sender: SenderType,
    val content: String,
    val chatID:Long,
    val createdAt: Long
) {
    fun toMessageRequest():MessageRequest {
        return when (sender) {
            SenderType.AI -> AssistantMessageRequest(
                content = content
            )
            SenderType.USER -> UserMessageRequest(
                content = content
            )
            else -> throw  Exception("Unknown sender type")
        }
    }
}

fun String.toDBMessage(chatID: Long): DBMessage = DBMessage(
    sender = SenderType.USER,
    content = this,
    chatID = chatID,
    createdAt =  System.currentTimeMillis()
)

