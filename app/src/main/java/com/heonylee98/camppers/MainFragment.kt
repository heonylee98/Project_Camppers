package com.heonylee98.camppers

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.heonylee98.camppers.databinding.FragmentMainBinding

class MainFragment : Fragment() {
    lateinit var fragmentMainBinding: FragmentMainBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentMainBinding = FragmentMainBinding.inflate(layoutInflater)
        navigationInit()

        return fragmentMainBinding.root
    }
    private fun navigationInit() {
        val navHostFragment = childFragmentManager.findFragmentById(R.id.fcvBottomNavigation) as NavHostFragment
        val navController = navHostFragment.navController

        fragmentMainBinding.bottomNavigationMain.run {
            setupWithNavController(navController)
        }
    }
}