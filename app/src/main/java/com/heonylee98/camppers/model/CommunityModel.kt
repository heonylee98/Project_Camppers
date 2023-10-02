package com.heonylee98.camppers.model

import android.util.Log
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.heonylee98.camppers.data.CommunityData

class CommunityModel {
    companion object {
        val db = Firebase.firestore
        fun communityUpload(post: CommunityData) {
            db.collection("communityPost")
                .add(post)
                .addOnSuccessListener { documentReference ->
                    Log.d("ModelTest", post.postUploadId)
                }
        }
    }
}