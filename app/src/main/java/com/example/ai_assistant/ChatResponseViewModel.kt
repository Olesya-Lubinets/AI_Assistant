package com.example.ai_assistant

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ai_assistant.models.ChatRequestModels.MessageRequest
import com.example.ai_assistant.models.UIModels.ChatMessageUI
import com.example.ai_assistant.models.dbModels.DBChat
import com.example.ai_assistant.models.dbModels.DBMessage
import com.example.ai_assistant.models.dbModels.toDBMessage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChatResponseViewModel @Inject constructor(
    private val apiRepository: APIResponseRepository,
    private val messageRepository: MessageRepository,
    private val chatRepository: ChatRepository
) : ViewModel() {

    private val _chatID = MutableStateFlow<Long?>(null)
    val chatID: StateFlow<Long?> = _chatID.asStateFlow()

   // private val _response = MutableSharedFlow<ChatResponse>()
   // val response: SharedFlow<ChatResponse> = _response.asSharedFlow()

    private val messagesFromDB: Flow<List<DBMessage>> =
        chatID
            .filterNotNull()
            .flatMapLatest {  messageRepository.getAllMessagesByChatID(it) }

    val messagesForAPI: StateFlow<List<MessageRequest>> =
       messagesFromDB
            .map { messages -> messages.map { it.toMessageRequest() }}
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000),
                initialValue = emptyList()
            )

    val messagesForUI:StateFlow<List<ChatMessageUI>> =
        messagesFromDB
            .map { messages -> messages.map { it.toChatMessageUI() }}
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000),
                initialValue = emptyList()
            )

    fun createChat() {
        viewModelScope.launch {
            val id = chatRepository.addChat(
                DBChat(
                    title = "Some chat name",
                    createdAt = System.currentTimeMillis() / 1000
                )
            )
            _chatID.value = id
        }
    }

    fun sendMessageAndGetResponse(query:String) {
        viewModelScope.launch {
            val currentChatID = chatID.value ?: return@launch
            val currentHistory = messagesForAPI.value

            val userMessage = query.toDBMessage(currentChatID)

            messageRepository.addMessage(userMessage)

            val updatedHistory =  currentHistory + userMessage.toMessageRequest()

            val modelResponse = apiRepository.getChatResponse(updatedHistory)
            messageRepository.addMessage(modelResponse.toDBMessage(currentChatID))
            //_response.emit(modelResponse)
        }
    }

}