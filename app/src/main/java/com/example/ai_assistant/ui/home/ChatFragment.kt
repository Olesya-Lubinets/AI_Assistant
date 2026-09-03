package com.example.ai_assistant.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ai_assistant.ChatResponseViewModel
import com.example.ai_assistant.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ChatFragment : Fragment() {

    val messageViewModel: ChatResponseViewModel by viewModels()
    private val args: ChatFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_new_chat, container, false)
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val chatID = args.chatID
        messageViewModel.setChatID(chatID)

        val searchView = view.findViewById<SearchView>(R.id.homeSearchView)
        val recyclerView = view.findViewById<RecyclerView>(R.id.homeRecyclerView)

        recyclerView.layoutManager = LinearLayoutManager(context)

        val adapter = MessageAdapter()
        recyclerView.adapter = adapter


        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                messageViewModel.messagesForUI.collect { messageHistory ->
                    Log.d("Observe chat history", messageHistory.joinToString())
                    adapter.submitList(messageHistory) {
                        if (messageHistory.isNotEmpty()) recyclerView.scrollToPosition(
                            messageHistory.lastIndex
                        )
                    }
                }
            }
        }



        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let {
                    messageViewModel.sendMessageAndGetResponse(query)
                }
                searchView.clearFocus()
                searchView.setQuery("", false)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }

        })

    }
}