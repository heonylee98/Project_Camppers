package com.heonylee98.camppers.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.heonylee98.camppers.adapter.UserChatAdapter
import com.heonylee98.camppers.databinding.FragmentUserChatBinding
import com.heonylee98.camppers.databinding.UserChatRecyclerRowBinding

class UserChatFragment : Fragment() {
    lateinit var fragmentUserChatBinding: FragmentUserChatBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentUserChatBinding = FragmentUserChatBinding.inflate(layoutInflater)

        return fragmentUserChatBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragmentUserChatBinding.run {
            recyclerUserChat.run {
                adapter = UserChatAdapter()
                layoutManager = LinearLayoutManager(context)
            }
        }
    }
}