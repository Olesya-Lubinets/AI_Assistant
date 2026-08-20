package com.example.ai_assistant.models.ChatResponseModels

import com.google.gson.annotations.SerializedName

class MessageResponse (
    val role: MessageRoleResponse,
    val content: String?,
    val created: Long?,
    val name: FunctionName?,

    @SerializedName("functions_state_id")
    val functionsStateId: String?,

    @SerializedName("function_call")
    val functionCall: FunctionCallResponse?
)
