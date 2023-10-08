package com.heonylee98.camppers.model

import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.heonylee98.camppers.data.UserData

class UserModel {
    companion object {
        // user uid를 문서 id로 지정하여 user 정보를 firestore에 업로드용 메서드
        fun userUpload(user: UserData) {
            val db = Firebase.firestore
            db.collection("userData").document(user.userUid)
                .set(user)
        }

        // user image, area 수정용 메서드
        fun userImageModify(user: UserData) {
            val db = Firebase.firestore
            db.collection("userData").document(user.userUid)
                .update("userImage", user.userImage)
        }
        fun userAreaModify(user: UserData) {
            val db = Firebase.firestore
            db.collection("userData").document(user.userUid)
                .update("userArea", user.userArea)
        }
    }
}