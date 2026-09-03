package com.example.ai_assistant.ui.home

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.ai_assistant.R
import android.view.ViewGroup
import android.view.LayoutInflater
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.ai_assistant.models.UIModels.ChatMessageUI
import com.example.ai_assistant.models.UIModels.SenderType



class MessageAdapter() : ListAdapter<ChatMessageUI, RecyclerView.ViewHolder >(MessageDiffCallBack()) {

    override fun getItemViewType(position: Int): Int {
        return when ( getItem(position).sender) {
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

    class MessageDiffCallBack: DiffUtil.ItemCallback<ChatMessageUI>()  {
        override fun areItemsTheSame(oldItem: ChatMessageUI, newItem: ChatMessageUI): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ChatMessageUI, newItem: ChatMessageUI): Boolean {
            return oldItem == newItem
        }
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
        val messageItem = getItem(position)
        when (holder){
            is UserMessageViewHolder -> holder.tvMessageText.text = messageItem.text
            is AiMessageViewHolder ->  holder.tvMessageText.text = messageItem.text
        }
    }
}