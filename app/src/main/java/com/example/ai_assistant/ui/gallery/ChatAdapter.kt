package com.example.ai_assistant.ui.gallery

import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.ai_assistant.R
import com.example.ai_assistant.models.dbModels.DBChat
import android.view.ViewGroup

class ChatAdapter(private val onItemClicked:(DBChat) -> Unit) : ListAdapter<DBChat, ChatAdapter.ChatViewHolder>(ChatDiffCallBack()) {

    class ChatViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val tvChatTitle = view.findViewById<TextView>(R.id.tvChatTitle)
        val tvChatDate = view.findViewById<TextView>(R.id.tvChatDate)
    }

    class ChatDiffCallBack: DiffUtil.ItemCallback<DBChat>() {
        override fun areItemsTheSame(oldItem: DBChat, newItem: DBChat): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: DBChat, newItem: DBChat): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.chat_item, parent, false)
        return ChatViewHolder(view)
    }

    override fun onBindViewHolder(holder: ChatViewHolder, position: Int) {
        val chatItem = getItem(position)
        holder.tvChatTitle.text = chatItem.title
        holder.tvChatDate.text = chatItem.createdAt.toString()

        holder.itemView.setOnClickListener {
            onItemClicked(chatItem)
        }
    }
}
