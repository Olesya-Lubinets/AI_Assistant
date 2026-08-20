package com.example.ai_assistant.models.ChatResponseModels

import com.google.gson.annotations.SerializedName

enum class FunctionName {

    @SerializedName("text2image")
    TEXT_TO_IMAGE,

    @SerializedName("text2model3d")
    TEXT_TO_MODEL_3D
}
