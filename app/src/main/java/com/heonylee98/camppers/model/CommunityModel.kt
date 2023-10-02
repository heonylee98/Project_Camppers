package com.heonylee98.camppers.model

import android.app.DownloadManager.Query
import android.util.Log
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.heonylee98.camppers.data.CommunityData

class CommunityModel {
    companion object {
        val db = Firebase.firestore

        // community upload model 메서드
        fun communityUpload(post: CommunityData) {
            db.collection("communityPost")
                .add(post)
                .addOnSuccessListener { documentReference ->
                    Log.d("ModelTest", post.postUploadId)
                }
        }

        // community post get model 메서드
        fun communityGetPost() {
            db.collection("communityPost").orderBy("postUploadTime")
                .get()
                .addOnSuccessListener { documents ->
                    for (doc in documents) {
                        val id = doc.data.get("postUploadId").toString()
                        val userId = doc.data.get("postUploadUserId").toString()
                        val time = doc.data.get("postUploadTime").toString()
                        val image = doc.data.get("postUploadImage").toString()
                        val text = doc.data.get("postUploadText").toString()
                        val like = doc.data.get("postUploadLike").toString().toBoolean()
                        val likeCount = doc.data.get("postUploadLikeCount").toString().toLong()
                        val comment = doc.data.get("postUploadComment").toString().toLong()

                        val communityGetPostData = CommunityData(id, userId, time, image,
                            text, like, likeCount, comment)
                    }
                }

        }
    }
}