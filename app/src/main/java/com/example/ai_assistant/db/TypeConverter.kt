package com.example.ai_assistant.db

import com.example.ai_assistant.models.UIModels.SenderType
import androidx.room.TypeConverter


class TypeConverter {
    @TypeConverter
    fun fromMessageSenderType(type: SenderType): String {
        return type.name
    }

    @TypeConverter
    fun toMessageSenderType(value: String): SenderType {
        return SenderType.valueOf(value)
    }
}
