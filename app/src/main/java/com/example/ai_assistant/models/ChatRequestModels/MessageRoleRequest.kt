package com.example.ai_assistant.models.ChatRequestModels

import com.google.gson.annotations.SerializedName

enum  class MessageRoleRequest {
 @SerializedName("system") SYSTEM,
    @SerializedName("assistant") ASSISTANT,
    @SerializedName("user") USER,
    @SerializedName("function") FUNCTION
}
