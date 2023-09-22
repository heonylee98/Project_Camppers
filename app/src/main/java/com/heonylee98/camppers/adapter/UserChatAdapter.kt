package com.heonylee98.camppers.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.heonylee98.camppers.databinding.UserChatRecyclerRowBinding

class UserChatAdapter: RecyclerView.Adapter<UserChatAdapter.UserChatViewHolder>() {
    inner class UserChatViewHolder(userChatRecyclerRowBinding: UserChatRecyclerRowBinding): RecyclerView.ViewHolder(userChatRecyclerRowBinding.root) {
        var userImage: ImageView
        var userNickname: TextView
        var lastTime: TextView
        var lastMessage: TextView

        init {
            userImage = userChatRecyclerRowBinding.imgRowUserChat
            userNickname = userChatRecyclerRowBinding.textRowNickname
            lastTime = userChatRecyclerRowBinding.textRowLastTime
            lastMessage = userChatRecyclerRowBinding.textRowLastMessage
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserChatViewHolder {
        val userChatRecyclerRowBinding =
            UserChatRecyclerRowBinding.inflate(LayoutInflater.from(parent.context))
        userChatRecyclerRowBinding.root.layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )

        return UserChatViewHolder(userChatRecyclerRowBinding)
    }
    override fun getItemCount(): Int {
        return 10
    }
    override fun onBindViewHolder(holder: UserChatViewHolder, position: Int) {
        holder.lastTime.text = "$position : 00"
    }
}