package com.heonylee98.camppers.view

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import com.heonylee98.camppers.data.CommunityData
import com.heonylee98.camppers.data.UserData
import com.heonylee98.camppers.databinding.FragmentCommunityAddBinding
import com.heonylee98.camppers.model.CommunityModel
import com.heonylee98.camppers.model.UserModel
import java.text.SimpleDateFormat

class CommunityAddFragment : Fragment() {
    lateinit var fragmentCommunityAddBinding: FragmentCommunityAddBinding
    lateinit var navController: NavController
    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fragmentCommunityAddBinding = FragmentCommunityAddBinding.inflate(layoutInflater)
        auth = FirebaseAuth.getInstance()

        fragmentCommunityAddBinding.run {
            buttonAddPostConfirm.setOnClickListener {
                addPost()
                navController = findNavController()
                navController.popBackStack()
            }
        }

        return fragmentCommunityAddBinding.root
    }

    // post upload용 메서드
    private fun addPost() {
        val uploadId = ""
        val uploadUserId = auth.currentUser?.displayName
        val uploadUserImage = auth.currentUser?.photoUrl.toString()
        val uploadTime = getPostTime()
        val uploadImage = "test_image"
        val uploadText = fragmentCommunityAddBinding.tiedtAddPost.text.toString()
        val uploadLike = false
        val uploadLikeCount = 0
        val uploadComment = 0

        val postUploadData = CommunityData(uploadId, uploadUserId!!, uploadUserImage,
            uploadTime, uploadImage, uploadText,
            uploadLike, uploadLikeCount.toLong(), uploadComment.toLong())

        CommunityModel.communityUpload(postUploadData)
    }

    // post upload time 계산용 메서드
    private fun getPostTime(): String {
        val now = System.currentTimeMillis()
        val sdf = SimpleDateFormat("MM-dd HH:mm")

        return sdf.format(now).toString()
    }
}