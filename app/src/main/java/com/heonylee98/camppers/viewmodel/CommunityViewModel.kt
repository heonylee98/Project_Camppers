package com.heonylee98.camppers.viewmodel

import android.net.Uri
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.heonylee98.camppers.MainActivity
import com.heonylee98.camppers.data.CommunityData
import com.heonylee98.camppers.data.UserData
import com.heonylee98.camppers.model.CommunityModel

class CommunityViewModel: ViewModel() {
    val communityList = MutableLiveData<MutableList<CommunityData>>()
    var likeBoolean = MutableLiveData<Boolean>()

    init {
        communityList.value = mutableListOf()
        likeBoolean.value = false
    }

    fun getBooleanOnPost(userUid: String, postId: String) {
        CommunityModel.getBooleanOnPost(userUid, postId) {
            likeBoolean.value = it.result.data?.get(userUid) as Boolean
        }
    }

    fun getCommunityData() {
        val dataList = mutableListOf<CommunityData>()

        CommunityModel.communityGetPost {
            for (doc in it.result.documents) {
                val id = doc.id
                val userId = doc.data?.get("postUploadUserId").toString()
                val userImage = doc.data?.get("postUploadUserImage").toString()
                val time = doc.data?.get("postUploadTime").toString()
                val image = doc.data?.get("postUploadImage").toString()
                val text = doc.data?.get("postUploadText").toString()

                // null일 경우에 못받아와서 non-null 처리 필요
                val like = if (doc.data?.get("postUploadLike") != null) {
                    doc.data?.get("postUploadLike") as Map<String, Boolean>
                } else {
                    null
                }
                val likeCount = doc.data?.get("postUploadLikeCount") as Long
                val comment = doc.data?.get("postUploadComment") as Long

                val communityGetPostData = CommunityData(id, userId, userImage, time, image,
                    text, like, likeCount, comment)

                dataList.add(communityGetPostData)
            }
            dataList.reverse()
            communityList.value = dataList
        }
    }

    fun likeButtonToggle(userUid: String, postId: String, toggle: Boolean) {
        val dataList = mutableListOf<CommunityData>()

        CommunityModel.communityLikeToggle2(userUid, postId, toggle) {
            CommunityModel.communityGetPost {
                for (doc in it.result.documents) {
                    val id = doc.id
                    val userId = doc.data?.get("postUploadUserId").toString()
                    val userImage = doc.data?.get("postUploadUserImage").toString()
                    val time = doc.data?.get("postUploadTime").toString()
                    val image = doc.data?.get("postUploadImage").toString()
                    val text = doc.data?.get("postUploadText").toString()

                    // null일 경우에 못받아와서 non-null 처리 필요
                    val like = if (doc.data?.get("postUploadLike") != null) {
                        doc.data?.get("postUploadLike") as Map<String, Boolean>
                    } else {
                        null
                    }
                    val likeCount = doc.data?.get("postUploadLikeCount") as Long
                    val comment = doc.data?.get("postUploadComment") as Long

                    val communityGetPostData = CommunityData(id, userId, userImage, time, image,
                        text, like, likeCount, comment)

                    dataList.add(communityGetPostData)
                }
                dataList.reverse()
                communityList.value = dataList
            }
        }
    }

    // like count up 메서드
    fun likeButtonCount1(postId: String) {
        val dataList = mutableListOf<CommunityData>()

        // like button을 누르면 communityLikeOn 메서드로 인해 LikeCount가 올라가게되고 리스트를 갱신하면 communityFragment의 observer가 확인할 수 있도록 적용
        CommunityModel.communityLikeDown(postId) {
            CommunityModel.communityGetPost {
                for (doc in it.result.documents) {
                    val id = doc.id
                    val userId = doc.data?.get("postUploadUserId").toString()
                    val userImage = doc.data?.get("postUploadUserImage").toString()
                    val time = doc.data?.get("postUploadTime").toString()
                    val image = doc.data?.get("postUploadImage").toString()
                    val text = doc.data?.get("postUploadText").toString()

                    // null일 경우에 못받아와서 non-null 처리 필요
                    val like = if (doc.data?.get("postUploadLike") != null) {
                        doc.data?.get("postUploadLike") as Map<String, Boolean>
                    } else {
                        null
                    }
                    val likeCount = doc.data?.get("postUploadLikeCount") as Long
                    val comment = doc.data?.get("postUploadComment") as Long

                    val communityGetPostData = CommunityData(id, userId, userImage, time, image,
                        text, like, likeCount, comment)

                    dataList.add(communityGetPostData)
                }
                dataList.reverse()
                communityList.value = dataList
            }
        }
    }

    // like count down 메서드
    fun likeButtonCount2(postId: String) {
        val dataList = mutableListOf<CommunityData>()

        // like button을 누르면 communityLikeOn 메서드로 인해 LikeCount가 올라가게되고 리스트를 갱신하면 communityFragment의 observer가 확인할 수 있도록 적용
        CommunityModel.communityLikeUp(postId) {
            CommunityModel.communityGetPost {
                for (doc in it.result.documents) {
                    val id = doc.id
                    val userId = doc.data?.get("postUploadUserId").toString()
                    val userImage = doc.data?.get("postUploadUserImage").toString()
                    val time = doc.data?.get("postUploadTime").toString()
                    val image = doc.data?.get("postUploadImage").toString()
                    val text = doc.data?.get("postUploadText").toString()

                    // null일 경우에 못받아와서 non-null 처리 필요
                    val like = if (doc.data?.get("postUploadLike") != null) {
                        doc.data?.get("postUploadLike") as Map<String, Boolean>
                    } else {
                        null
                    }
                    val likeCount = doc.data?.get("postUploadLikeCount") as Long
                    val comment = doc.data?.get("postUploadComment") as Long

                    val communityGetPostData = CommunityData(id, userId, userImage, time, image,
                        text, like, likeCount, comment)

                    dataList.add(communityGetPostData)
                }
                dataList.reverse()
                communityList.value = dataList
            }
        }
    }
}