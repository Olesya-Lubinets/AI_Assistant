package com.example.ai_assistant.ui.gallery

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ai_assistant.ChatViewModel
import com.example.ai_assistant.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ChatListFragment : Fragment() {

    val chatViewModel: ChatViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_list_chats, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btn_newChat = view.findViewById<Button>(R.id.btn_newChat)

        val chatListRecyclerView = view.findViewById<RecyclerView>(R.id.ChatListRecyclerView)
        chatListRecyclerView.layoutManager = LinearLayoutManager(context)

        val adapter = ChatAdapter { chat ->
            val action = ChatListFragmentDirections.actionChatListFragmentToChatFragment(chat.id)
            findNavController().navigate(action)
        }
        chatListRecyclerView.adapter = adapter

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                chatViewModel.listOfChats.collect { chatList ->
                    adapter.submitList(chatList)
                }
            }
        }

        btn_newChat.setOnClickListener {
            chatViewModel.createChat()
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                chatViewModel.chatID.collect { id ->
                    val action = ChatListFragmentDirections.actionChatListFragmentToChatFragment(id)
                    findNavController().navigate(action)
                }
            }
        }
    }
}