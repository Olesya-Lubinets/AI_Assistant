package com.example.ai_assistant.models.ChatRequestModels

import com.google.gson.annotations.SerializedName

interface FunctionCallRequest

enum class FunctionCallRequestMode:FunctionCallRequest {
    @SerializedName("none") NONE,
    @SerializedName("auto") AUTO
}
data  class FunctionCallRequestName(val name: String) :FunctionCallRequest
