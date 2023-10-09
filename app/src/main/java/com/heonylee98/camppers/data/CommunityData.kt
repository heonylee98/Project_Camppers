package com.heonylee98.camppers.data

import android.net.Uri

data class CommunityData(val postUploadId: String,
                         val postUploadUserId: String,
                         val postUploadUserImage: String,
                         val postUploadTime: String,
                         val postUploadImage: String,
                         val postUploadText: String,

                         // 사용자 uid와 좋아요 toggle 입력 받아 저장 초기값은 null
                         val postUploadLike: Map<String, Boolean>?,
                         val postUploadLikeCount: Long,
                         val postUploadComment: Long)
