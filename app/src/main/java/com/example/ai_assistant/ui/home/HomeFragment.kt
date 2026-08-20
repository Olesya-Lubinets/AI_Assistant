package com.example.ai_assistant.ui.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ai_assistant.ChatResponseViewModel
import com.example.ai_assistant.R
import com.example.ai_assistant.models.ChatRequestModels.AssistantMessageRequest
import com.example.ai_assistant.models.ChatRequestModels.MessageRequest
import com.example.ai_assistant.models.ChatRequestModels.UserMessageRequest
import com.example.ai_assistant.models.UIModels.ChatMessageUI
import com.example.ai_assistant.models.UIModels.SenderType
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

//    val viewModel: HomeViewModel by viewModels()
    val chatViewModel:ChatResponseViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    @SuppressLint("UnsafeRepeatOnLifecycleDetector")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val searchView = view.findViewById<SearchView>(R.id.homeSearchView)
        val recyclerView = view.findViewById<RecyclerView>(R.id.homeRecyclerView)

        recyclerView.layoutManager = LinearLayoutManager(context)

        val adapter = MessageAdapter()
        recyclerView.adapter = adapter


        chatViewModel.uiChatHistory.observe(viewLifecycleOwner) { messageHistory ->
            adapter.submitList( messageHistory)
            recyclerView.scrollToPosition( messageHistory.lastIndex)
        }

        chatViewModel.response.observe(viewLifecycleOwner) { response ->
           val responseMessage = response.choices.first().message.content?: ""
            chatViewModel.addToUIChatHistory(ChatMessageUI(SenderType.AI, responseMessage))
            chatViewModel.addToAPIMessagesHistory(AssistantMessageRequest(content = responseMessage))
        }

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let {
                    chatViewModel.addToAPIMessagesHistory(UserMessageRequest(content = query))
                    chatViewModel.addToUIChatHistory(ChatMessageUI(SenderType.USER,query))
                    chatViewModel.getResponse(chatViewModel.getHistoryMessagesOnce())
                }
               searchView.clearFocus()
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
               return true
            }

        })

    }
}