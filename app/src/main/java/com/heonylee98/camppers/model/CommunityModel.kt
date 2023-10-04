package com.heonylee98.camppers.model

import android.app.DownloadManager.Query
import android.content.Context
import android.util.Log
import com.google.android.gms.tasks.Task
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.heonylee98.camppers.data.CommunityData

class CommunityModel {
    companion object {
        // community upload model 메서드
        fun communityUpload(post: CommunityData) {
            val db = Firebase.firestore
            db.collection("communityPost")
                .add(post)
                .addOnSuccessListener { documentReference ->
                    // documentId로 postUploadId 교체
                    val docId = documentReference.id
                    db.collection("communityPost").document(docId)
                        .update("postUploadId", docId)
                }
        }
        fun communityLikeOn(postId: String, callback: (Task<Void>) -> Unit) {
            val db = Firebase.firestore
            db.collection("communityPost").document(postId)
                // user 많아지면 초당 1회 이상 like button 활용을 위해 분산 카운터 활용하기
                .update("postUploadLikeCount", FieldValue.increment(1))
                .addOnCompleteListener(callback)
        }

        // community post get model 메서드
        fun communityGetPost(callback: (Task<QuerySnapshot>) -> Unit) {
            val db = Firebase.firestore
            db.collection("communityPost").orderBy("postUploadTime")
                .get()
                .addOnCompleteListener(callback)
        }
    }
}