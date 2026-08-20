package com.example.ai_assistant.models.ChatRequestModels

import com.google.gson.JsonObject
import com.google.gson.annotations.SerializedName

enum class ResponseFormatType {
    @SerializedName("text") TEXT,@SerializedName("json_schema") JSONSCHEMA
}

class ResponseFormat(
    val type: ResponseFormatType,
    val schema:JsonObject? =null,
    val strict:Boolean? = null
)