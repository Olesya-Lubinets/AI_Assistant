package com.example.ai_assistant.models.ChatRequestModels

import com.example.ai_assistant.models.ChatResponseModels.FunctionCallResponse

data class ChatRequest(
    val model: String,
    val messages: List<MessageRequest>,

    val function_call: FunctionCallRequest? = null,
    val functions: List<FunctionRequest>? = null,

    val temperature: Double? = null,
    val top_p: Double? = null,
    val stream: Boolean = false,
    val max_tokens: Int? = null,
    val repetition_penalty: Double? = null,
    val update_interval: Double? = null,

    val response_format: ResponseFormat? = null,
)