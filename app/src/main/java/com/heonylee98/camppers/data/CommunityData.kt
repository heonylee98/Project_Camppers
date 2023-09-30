package com.heonylee98.camppers.data

data class CommunityData(val postUploadUserId: String,
                         val postUploadTime: String,
                         val postUploadImage: String,
                         val postUploadText: String,
                         val postUploadLike: Boolean,
                         val postUploadLikeCount: Long,
                         val postUploadComment: CommunityComment)
