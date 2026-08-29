package com.example.ai_assistant.models.ChatRequestModels

import com.example.ai_assistant.models.ChatResponseModels.FunctionCallResponse
import com.example.ai_assistant.models.UIModels.ChatMessageUI
import com.example.ai_assistant.models.UIModels.SenderType


interface MessageRequest {
    val role: MessageRoleRequest
    val content: String

    fun toUI():ChatMessageUI
}

data class SystemMessageRequest(
    override val role: MessageRoleRequest = MessageRoleRequest.SYSTEM, override val content: String,
) : MessageRequest {
    override fun toUI(): ChatMessageUI {
        return ChatMessageUI(SenderType.AI,content) // THis is not correct
    }
}

data class AssistantMessageRequest(
    override val role: MessageRoleRequest = MessageRoleRequest.ASSISTANT,
    val function_call: FunctionCallResponse? = null,
    val functions_state_id: String? = null,
    override val content: String
) : MessageRequest {
    override fun toUI(): ChatMessageUI {
        return ChatMessageUI(SenderType.AI,content)
    }
}


data class UserMessageRequest(
    override val role: MessageRoleRequest = MessageRoleRequest.USER,
    val attachments: List<String>? = null,
    override val content: String
) : MessageRequest {
    override fun toUI(): ChatMessageUI {
        return ChatMessageUI(SenderType.USER,content)
    }
}

data class FunctionMessageRequest(
    override val role: MessageRoleRequest = MessageRoleRequest.FUNCTION,
    override val content: String,
    ) : MessageRequest {
    override fun toUI(): ChatMessageUI {
        return ChatMessageUI(SenderType.AI,content) // THis is not correct
    }
}

