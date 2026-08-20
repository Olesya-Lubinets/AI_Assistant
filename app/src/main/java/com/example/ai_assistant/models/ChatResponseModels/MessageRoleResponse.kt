package com.example.ai_assistant.models.ChatResponseModels

import com.google.gson.annotations.SerializedName


enum class MessageRoleResponse {

    @SerializedName("assistant")
    ASSISTANT,

    @SerializedName("function_in_progress")
    FUNCTION_IN_PROGRESS
}

