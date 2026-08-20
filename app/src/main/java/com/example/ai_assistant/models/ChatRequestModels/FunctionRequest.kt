package com.example.ai_assistant.models.ChatRequestModels

import android.media.audiofx.AudioEffect.Descriptor
import com.google.gson.JsonObject

class FunctionRequest (
    val name:String,
    val description: String? = null,
    val parameters: JsonObject,
    val few_shot_examples: List<ExampleRequest>? = null,
    val return_parameters: JsonObject? = null
)