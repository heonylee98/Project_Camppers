package com.heonylee98.camppers.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.heonylee98.camppers.R
import com.heonylee98.camppers.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    lateinit var fragmentHomeBinding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentHomeBinding = FragmentHomeBinding.inflate(layoutInflater)
        // 배너 광고 & 공지 사항
        // 캠핑 중고 물품
        // 인기 게시글 top 5
        // 인기 캠핑장 top 5
        return fragmentHomeBinding.root
    }
}