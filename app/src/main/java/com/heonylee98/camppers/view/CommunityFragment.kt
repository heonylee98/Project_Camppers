package com.heonylee98.camppers.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.heonylee98.camppers.R
import com.heonylee98.camppers.databinding.FragmentCommunityBinding

class CommunityFragment : Fragment() {
    lateinit var fragmentCommunityBinding: FragmentCommunityBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentCommunityBinding = FragmentCommunityBinding.inflate(layoutInflater)

        return fragmentCommunityBinding.root
    }
}