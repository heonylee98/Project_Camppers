package com.heonylee98.camppers.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.heonylee98.camppers.R
import com.heonylee98.camppers.adapter.CommunityAdapter
import com.heonylee98.camppers.databinding.FragmentCommunityBinding
import com.heonylee98.camppers.model.CommunityModel

class CommunityFragment : Fragment() {
    lateinit var fragmentCommunityBinding: FragmentCommunityBinding
    lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentCommunityBinding = FragmentCommunityBinding.inflate(layoutInflater)
        navController = findNavController()
        CommunityModel.communityGetPost()

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
}