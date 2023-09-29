package com.heonylee98.camppers.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.heonylee98.camppers.databinding.CommunityRecyclerRowBinding

class CommunityAdapter: RecyclerView.Adapter<CommunityAdapter.CommunityHolder>() {
    inner class CommunityHolder(communityRecyclerRowBinding: CommunityRecyclerRowBinding): RecyclerView.ViewHolder(communityRecyclerRowBinding.root) {
        var userImage: ImageView
        var userNickname: TextView
        var postUploadDate: TextView
        var postImage: ImageView
        var postText: TextView

        var likeButton: Button
        var commentButton: Button
        var shareButton: Button

        init {
            userImage = communityRecyclerRowBinding.recyclerUserImage
            userNickname = communityRecyclerRowBinding.recyclerUserNickname
            postUploadDate = communityRecyclerRowBinding.recyclerPostedDate
            postImage = communityRecyclerRowBinding.recyclerPostImage
            postText = communityRecyclerRowBinding.recyclerPostText

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
        return 10
    }
    override fun onBindViewHolder(holder: CommunityHolder, position: Int) {
        holder.postUploadDate.text = "$position"
    }
}