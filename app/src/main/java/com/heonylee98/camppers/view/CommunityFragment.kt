package com.heonylee98.camppers.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.heonylee98.camppers.MainActivity
import com.heonylee98.camppers.R
import com.heonylee98.camppers.databinding.CommunityRecyclerRowBinding
import com.heonylee98.camppers.databinding.FragmentCommunityBinding
import com.heonylee98.camppers.model.CommunityModel
import com.heonylee98.camppers.viewmodel.CommunityViewModel

class CommunityFragment : Fragment() {
    lateinit var fragmentCommunityBinding: FragmentCommunityBinding
    lateinit var mainActivity: MainActivity
    lateinit var navController: NavController
    lateinit var communityViewModel: CommunityViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentCommunityBinding = FragmentCommunityBinding.inflate(layoutInflater)
        mainActivity = activity as MainActivity
        navController = findNavController()
        communityViewModel = ViewModelProvider(mainActivity)[CommunityViewModel::class.java]

        communityViewModel.run {
            communityList.observe(mainActivity) {
                fragmentCommunityBinding.recyclerCommunity.adapter?.notifyDataSetChanged()
            }
        }

        fragmentCommunityBinding.run {
            buttonCommunityUpload.setOnClickListener {
                navController.navigate(R.id.communityAddFragment)
            }
            communityViewModel.getCommunityData()
        }

        return fragmentCommunityBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragmentCommunityBinding.run {
            recyclerCommunity.run {
                adapter = CommunityAdapter()
                layoutManager = LinearLayoutManager(context)
            }
        }
    }

    inner class CommunityAdapter: RecyclerView.Adapter<CommunityAdapter.CommunityHolder>() {
        inner class CommunityHolder(communityRecyclerRowBinding: CommunityRecyclerRowBinding): RecyclerView.ViewHolder(communityRecyclerRowBinding.root) {
            var userImage: ImageView
            var userNickname: TextView
            var postUploadDate: TextView
            var postImage: ImageView
            var postText: TextView
            var postLikeCount: TextView

            var likeButton: Button
            var commentButton: Button
            var shareButton: Button

            init {
                userImage = communityRecyclerRowBinding.recyclerUserImage
                userNickname = communityRecyclerRowBinding.recyclerUserNickname
                postUploadDate = communityRecyclerRowBinding.recyclerPostedDate
                postImage = communityRecyclerRowBinding.recyclerPostImage
                postText = communityRecyclerRowBinding.recyclerPostText
                postLikeCount = communityRecyclerRowBinding.recyclerLikeCount

                likeButton = communityRecyclerRowBinding.recyclerLike
                commentButton = communityRecyclerRowBinding.recyclerComment
                shareButton = communityRecyclerRowBinding.recyclerShare
            }

        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommunityHolder {
            val communityRecyclerRowBinding =
                CommunityRecyclerRowBinding.inflate(LayoutInflater.from(parent.context))

            communityRecyclerRowBinding.root.layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )

            return CommunityHolder(communityRecyclerRowBinding)
        }
        override fun getItemCount(): Int {
            return communityViewModel.communityList.value?.size!!
        }
        override fun onBindViewHolder(holder: CommunityHolder, position: Int) {
            holder.postText.text = communityViewModel.communityList.value?.get(position)?.postUploadText
        }
    }
}