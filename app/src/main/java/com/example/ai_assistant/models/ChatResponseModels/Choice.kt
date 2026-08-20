package com.example.ai_assistant.models.ChatResponseModels

import com.google.gson.annotations.SerializedName

data class Choice (

        val message: MessageResponse,
        val index: Int,
        @SerializedName("finish_reason")
        val finishReason: FinishReason?
)