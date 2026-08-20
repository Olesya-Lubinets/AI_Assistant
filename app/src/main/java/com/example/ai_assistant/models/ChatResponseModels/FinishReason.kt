package com.example.ai_assistant.models.ChatResponseModels

import com.google.gson.annotations.SerializedName

enum class FinishReason {
    @SerializedName("stop")
    STOP,

    @SerializedName("length")
    LENGTH,

    @SerializedName("function_call")
    FUNCTION_CALL,

    @SerializedName("blacklist")
    BLACKLIST,

    @SerializedName("error")
    ERROR
}