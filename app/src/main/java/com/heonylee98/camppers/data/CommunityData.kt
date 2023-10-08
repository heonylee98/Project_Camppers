package com.heonylee98.camppers.data

import android.net.Uri

data class CommunityData(val postUploadId: String,
                         val postUploadUserId: String,
                         val postUploadUserImage: String,
                         val postUploadTime: String,
                         val postUploadImage: String,
                         val postUploadText: String,
                         val postUploadLike: Boolean,
                         val postUploadLikeCount: Long,
                         val postUploadComment: Long)
