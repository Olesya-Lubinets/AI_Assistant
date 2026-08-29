package com.example.ai_assistant

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ai_assistant.models.dbModels.DBChat
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(private val chatRepository: ChatRepository) : ViewModel() {

/*    private val _chatID = MutableSharedFlow<Long>()
    val chatID = _chatID.asSharedFlow()

    fun createChat() {
        viewModelScope.launch {
            val id = chatRepository.addChat(
                DBChat(
                    title = "Some chat name",
                    createdAt = System.currentTimeMillis()
                )
            )
            _chatID.emit(id)
        }
    }*/

}