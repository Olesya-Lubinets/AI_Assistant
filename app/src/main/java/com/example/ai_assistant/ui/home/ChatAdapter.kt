package com.example.ai_assistant.ui.home

import android.annotation.SuppressLint
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.ai_assistant.R
import com.example.ai_assistant.models.ChatResponseModels.Choice
import android.view.ViewGroup
import android.view.LayoutInflater
import com.example.ai_assistant.models.UIModels.ChatMessageUI
import com.example.ai_assistant.models.UIModels.SenderType

class MessageAdapter() : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    val messageList = mutableListOf<ChatMessageUI>()

    @SuppressLint("NotifyDataSetChanged")
    fun submitList(newChoiceList:List<ChatMessageUI>) {
        messageList.clear()
        messageList.addAll(newChoiceList)
        notifyDataSetChanged()
    }
    override fun getItemViewType(position: Int): Int {
        return when (messageList[position].sender) {
            SenderType.USER -> 0
            SenderType.AI -> 1
        }
    }


    class UserMessageViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvMessageText: TextView =
            view.findViewById(R.id.userMessageText)
    }

    class AiMessageViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvMessageText: TextView =
            view.findViewById(R.id.AImessageText)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        when (viewType) {
             0 -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(
                        R.layout.user_message_item,
                        parent,
                        false
                    )

                 return UserMessageViewHolder(view)
            }
            1 -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(
                        R.layout.ai_message_item,
                        parent,
                        false
                    )

                return AiMessageViewHolder(view)
            }
            else ->  throw IllegalArgumentException("Unknown viewType: $viewType")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val messageItem = messageList[position]
        when (holder){
            is UserMessageViewHolder -> holder.tvMessageText.text = messageItem.text
            is AiMessageViewHolder ->  holder.tvMessageText.text = messageItem.text
        }


    }
    override fun getItemCount(): Int = messageList.size
}