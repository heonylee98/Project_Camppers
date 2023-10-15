package com.heonylee98.camppers.view

import android.opengl.Visibility
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.heonylee98.camppers.data.ChatData
import com.heonylee98.camppers.data.ChatRoomData
import com.heonylee98.camppers.databinding.FragmentUserChatBinding
import com.heonylee98.camppers.databinding.UserChatRecyclerRowBinding
import com.heonylee98.camppers.model.ChatModel

class UserChatFragment : Fragment() {
    lateinit var fragmentUserChatBinding: FragmentUserChatBinding
    lateinit var auth: FirebaseAuth
    private var checkBoxTemp = 0
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentUserChatBinding = FragmentUserChatBinding.inflate(layoutInflater)
        auth = FirebaseAuth.getInstance()

        return fragmentUserChatBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragmentUserChatBinding.run {
            recyclerUserChat.run {
                adapter = UserChatAdapter()
                layoutManager = LinearLayoutManager(context)
            }
            loadCheckBox()
            btnDeleteUserChat.setOnClickListener {
                /*val myUid = auth.uid.toString()
                val otherUid = "test_uid"

                val chatRoom = ChatRoomData(myUid, otherUid, null)
                ChatModel.createChatRoom(chatRoom)*/
                chatLogTest()
            }
        }
    }

    private fun chatLogTest() {
        val uid = auth.uid.toString()
        val testTime = "17:34"
        val testText = "안녕하세요"

        val chatTest = ChatData(uid, testTime, testText)
        ChatModel.chatOnChatRoom("Udw1UTA1TL5izOYltJjr" , chatTest)
    }

    private fun loadCheckBox() {
        fragmentUserChatBinding.btnSelectUserChat.setOnClickListener {
            val viewInt = checkBoxTemp

            if (viewInt != 1) {
                checkBoxTemp = 1
                fragmentUserChatBinding.recyclerUserChat.adapter?.notifyDataSetChanged()
            }
            else {
                checkBoxTemp = 0
                fragmentUserChatBinding.recyclerUserChat.adapter?.notifyDataSetChanged()
            }
        }
    }

    inner class UserChatAdapter: RecyclerView.Adapter<UserChatAdapter.UserChatViewHolder>() {
        inner class UserChatViewHolder(userChatRecyclerRowBinding: UserChatRecyclerRowBinding): RecyclerView.ViewHolder(userChatRecyclerRowBinding.root) {
            var userNickname: TextView
            var lastTime: TextView
            var lastMessage: TextView
            var checkBox: CheckBox

            init {
                userNickname = userChatRecyclerRowBinding.textRowNickname
                lastTime = userChatRecyclerRowBinding.textRowLastTime
                lastMessage = userChatRecyclerRowBinding.textRowLastMessage
                checkBox = userChatRecyclerRowBinding.checkBox
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

            if (checkBoxTemp != 0){
                holder.checkBox.visibility = View.VISIBLE
            } else {
                holder.checkBox.visibility = View.GONE
            }
        }
    }
}