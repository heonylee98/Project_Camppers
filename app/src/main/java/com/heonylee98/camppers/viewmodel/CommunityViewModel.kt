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
                val id = doc.data?.get("postUploadId").toString()
                val userId = doc.data?.get("postUploadUserId").toString()
                val time = doc.data?.get("postUploadTime").toString()
                val image = doc.data?.get("postUploadImage").toString()
                val text = doc.data?.get("postUploadText").toString()
                val like = doc.data?.get("postUploadLike").toString().toBoolean()
                val likeCount = doc.data?.get("postUploadLikeCount").toString().toLong()
                val comment = doc.data?.get("postUploadComment").toString().toLong()

                val communityGetPostData = CommunityData(id, userId, time, image,
                    text, like, likeCount, comment)

                dataList.add(communityGetPostData)
            }

            communityList.value = dataList
        }
    }
}