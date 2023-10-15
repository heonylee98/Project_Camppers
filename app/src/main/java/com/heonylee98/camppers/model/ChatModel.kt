package com.heonylee98.camppers.model

import com.google.firebase.firestore.SetOptions
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.heonylee98.camppers.data.ChatData
import com.heonylee98.camppers.data.ChatRoomData
import java.text.SimpleDateFormat

class ChatModel {
    companion object {

        fun getMyChatLog() {

        }
        fun deleteMyChatLog() {

        }
        fun createChatRoom(chatRoomData: ChatRoomData) {
            val db = Firebase.firestore
            db.collection("chatRoomData")
                .add(chatRoomData)
                .addOnCompleteListener {
                    db.collection("chatRoomData").document(it.result.id)
                        .update("chatRoomUid", it.result.id)
                }
        }

        fun chatOnChatRoom(chatRoomUid: String, chatData: ChatData) {
            val db = Firebase.firestore
            val parse = mapOf(
                "CD" + getPostTime() to chatData
            )
            db.collection("chatRoomData").document(chatRoomUid)
                .set(parse, SetOptions.merge())
        }
        private fun getPostTime(): String {
            val now = System.currentTimeMillis()
            val sdf = SimpleDateFormat("ss:ms")

            return sdf.format(now).toString()
        }
    }
}