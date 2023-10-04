package com.heonylee98.camppers.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.heonylee98.camppers.MainActivity
import com.heonylee98.camppers.data.CommunityData
import com.heonylee98.camppers.model.CommunityModel

class CommunityViewModel: ViewModel() {
    val communityList = MutableLiveData<MutableList<CommunityData>>()

    init {
        communityList.value = mutableListOf()
    }

    fun getCommunityData() {
        val dataList = mutableListOf<CommunityData>()

        CommunityModel.communityGetPost {
            for (doc in it.result.documents) {
                val id = doc.id
                val userId = doc.data?.get("postUploadUserId").toString()
                val time = doc.data?.get("postUploadTime").toString()
                val image = doc.data?.get("postUploadImage").toString()
                val text = doc.data?.get("postUploadText").toString()
                val like = doc.data?.get("postUploadLike").toString().toBoolean()
                val likeCount = doc.data?.get("postUploadLikeCount") as Long
                val comment = doc.data?.get("postUploadComment") as Long

                val communityGetPostData = CommunityData(id, userId, time, image,
                    text, like, likeCount, comment)

                dataList.add(communityGetPostData)
            }

            communityList.value = dataList
        }
    }

    fun likeButtonCount(postId: String) {
        val dataList = mutableListOf<CommunityData>()

        // like button을 누르면 communityLikeOn 메서드로 인해 LikeCount가 올라가게되고 리스트를 갱신하면 communityFragment의 observer가 확인할 수 있도록 적용
        CommunityModel.communityLikeOn(postId) {
            CommunityModel.communityGetPost {
                for (doc in it.result.documents) {
                    val id = doc.id
                    val userId = doc.data?.get("postUploadUserId").toString()
                    val time = doc.data?.get("postUploadTime").toString()
                    val image = doc.data?.get("postUploadImage").toString()
                    val text = doc.data?.get("postUploadText").toString()
                    val like = doc.data?.get("postUploadLike").toString().toBoolean()
                    val likeCount = doc.data?.get("postUploadLikeCount") as Long
                    val comment = doc.data?.get("postUploadComment") as Long

                    val communityGetPostData = CommunityData(id, userId, time, image,
                        text, like, likeCount, comment)

                    dataList.add(communityGetPostData)
                }

                communityList.value = dataList
            }
        }
    }
}