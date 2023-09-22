package com.heonylee98.camppers

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.heonylee98.camppers.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var activityMainBinding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        navigationInit()

        setContentView(activityMainBinding.root)
    }

    private fun navigationInit() {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fcvMain) as NavHostFragment
        val navController = navHostFragment.navController

        activityMainBinding.bottomNavigationMain.run {
            setupWithNavController(navController)
        }
    }
}