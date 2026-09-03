package com.example.ai_assistant

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ai_assistant.models.dbModels.DBChat
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(private val chatRepository: ChatRepository) : ViewModel() {

   private val _chatID = MutableSharedFlow<Long>()
    val chatID = _chatID.asSharedFlow()

    fun createChat() {
        viewModelScope.launch {
            try {
                val id = chatRepository.addChat(
                    DBChat(
                        title = "Some chat name",
                        createdAt = System.currentTimeMillis()
                    )
                )
                _chatID.emit(id)
            } catch (e:Exception) {
                Log.d("ChatViewModel","New chat is not created")
            }
        }
    }

    val listOfChats:StateFlow<List<DBChat>> = chatRepository.getAllChats().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = emptyList()
    )
}