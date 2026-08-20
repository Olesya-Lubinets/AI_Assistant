package com.example.ai_assistant

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ai_assistant.models.ChatRequestModels.MessageRequest
import com.example.ai_assistant.models.ChatResponseModels.ChatResponse
import com.example.ai_assistant.models.UIModels.ChatMessageUI
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChatResponseViewModel @Inject constructor(private val repository:ChatResponseRepository):ViewModel() {

    private val _response = MutableLiveData<ChatResponse> ()
    val response:LiveData<ChatResponse> = _response

    private val _apiMessagesHistory = MutableLiveData<List<MessageRequest>>(emptyList())
    val apiMessagesHistory:LiveData<List<MessageRequest>> = _apiMessagesHistory

    private val _uiChatHistory = MutableLiveData<List<ChatMessageUI>>(emptyList())
    val uiChatHistory:LiveData<List<ChatMessageUI>> = _uiChatHistory

    fun getResponse(request: List<MessageRequest>) {
        viewModelScope.launch {
         _response.value = repository.getChatResponse(request) }
    }

    fun addToAPIMessagesHistory(message:MessageRequest) {
        _apiMessagesHistory.value = apiMessagesHistory.value + message
    }

    fun addToUIChatHistory(message:ChatMessageUI) {
        _uiChatHistory.value = uiChatHistory.value + message
    }

    fun getHistoryMessagesOnce():List<MessageRequest> = apiMessagesHistory.value ?: emptyList()
}