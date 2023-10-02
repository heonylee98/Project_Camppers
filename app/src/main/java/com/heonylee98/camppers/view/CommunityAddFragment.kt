package com.heonylee98.camppers.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.heonylee98.camppers.data.CommunityData
import com.heonylee98.camppers.databinding.FragmentCommunityAddBinding
import com.heonylee98.camppers.model.CommunityModel
import java.text.SimpleDateFormat

class CommunityAddFragment : Fragment() {
    lateinit var fragmentCommunityAddBinding: FragmentCommunityAddBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentCommunityAddBinding = FragmentCommunityAddBinding.inflate(layoutInflater)
        fragmentCommunityAddBinding.run {
            buttonAddPostConfirm.setOnClickListener {
                addPost()
            }
        }

        return fragmentCommunityAddBinding.root
    }
    private fun addPost() {
        val uploadId = "test_post_id"
        val uploadUserId = "test_user_id"
        val uploadTime = getPostTime()
        val uploadImage = "test_image"
        val uploadText = fragmentCommunityAddBinding.tiedtAddPost.text.toString()
        val uploadLike = false
        val uploadLikeCount = 0

        val postUploadData = CommunityData(uploadId, uploadUserId,
            uploadTime, uploadImage, uploadText,
            uploadLike, uploadLikeCount.toLong(), null)

        CommunityModel.communityUpload(postUploadData)
    }
    private fun getPostTime(): String {
        val now = System.currentTimeMillis()
        val sdf = SimpleDateFormat("MM-dd-hh-mm")

        return sdf.format(now).toString()
    }
}