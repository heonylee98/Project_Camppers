package com.heonylee98.camppers.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseAuth
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

    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentCommunityBinding = FragmentCommunityBinding.inflate(layoutInflater)
        mainActivity = activity as MainActivity
        navController = findNavController()
        communityViewModel = ViewModelProvider(mainActivity)[CommunityViewModel::class.java]

        auth = FirebaseAuth.getInstance()

        communityViewModel.run {
            communityList.observe(mainActivity) {
                fragmentCommunityBinding.recyclerCommunity.adapter?.notifyDataSetChanged()
            }
            getCommunityData()
        }

        fragmentCommunityBinding.run {
            buttonCommunityUpload.setOnClickListener {
                navController.navigate(R.id.communityAddFragment)
            }
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
        val currentUser = auth.currentUser
        inner class CommunityHolder(communityRecyclerRowBinding: CommunityRecyclerRowBinding): RecyclerView.ViewHolder(communityRecyclerRowBinding.root) {
            var userImage: ImageView
            var userNickname: TextView
            var postUploadDate: TextView
            var postImage: ImageView
            var postText: TextView
            var postLikeCount: TextView

            var likeButton: CheckBox
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
                likeButton.setOnCheckedChangeListener { compoundButton, b ->

                    communityViewModel.getBooleanOnPost(currentUser?.uid!!, communityViewModel.communityList.value?.get(adapterPosition)?.postUploadId!!)

                    // likeBoolean.value 값을 getBooleanOnPost메서드를 통해 누를 때마다 변화 시켜 observer를 통해 가져와 equals메서드를 통해 값을 분기한 후에 like 갯수 처리와 사용자 toggle 판단
                    if (communityViewModel.likeBoolean.value?.equals(true)!!) {
                        communityViewModel.likeButtonCount1(communityViewModel.communityList.value?.get(adapterPosition)?.postUploadId!!)
                        communityViewModel.likeButtonToggle(currentUser?.uid!!, communityViewModel.communityList.value?.get(adapterPosition)?.postUploadId!!, false)
                    }
                    else if (communityViewModel.likeBoolean.value?.equals(false)!!){
                        communityViewModel.likeButtonCount2(communityViewModel.communityList.value?.get(adapterPosition)?.postUploadId!!)
                        communityViewModel.likeButtonToggle(currentUser?.uid!!, communityViewModel.communityList.value?.get(adapterPosition)?.postUploadId!!, true)
                    }
                }
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
            holder.userNickname.text = communityViewModel.communityList.value?.get(position)?.postUploadUserId
            Glide.with(this@CommunityFragment)
                .load(communityViewModel.communityList.value?.get(position)?.postUploadUserImage)
                .into(holder.userImage)
            holder.postUploadDate.text = communityViewModel.communityList.value?.get(position)?.postUploadTime
            holder.postText.text = communityViewModel.communityList.value?.get(position)?.postUploadText
            holder.postLikeCount.text = "좋아요 ${communityViewModel.communityList.value?.get(position)?.postUploadLikeCount}개"
        }
    }
}