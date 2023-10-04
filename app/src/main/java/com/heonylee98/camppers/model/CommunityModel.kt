package com.heonylee98.camppers.model

import android.app.DownloadManager.Query
import android.content.Context
import android.util.Log
import com.google.android.gms.tasks.Task
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.firestore.QuerySnapshot
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
        fun communityGetPost(callback: (Task<QuerySnapshot>) -> Unit) {
            db.collection("communityPost").orderBy("postUploadTime")
                .get()
                .addOnCompleteListener(callback)
        }
    }
}