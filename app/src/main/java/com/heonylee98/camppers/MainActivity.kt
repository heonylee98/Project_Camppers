package com.heonylee98.camppers

import android.Manifest
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.heonylee98.camppers.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var activityMainBinding: ActivityMainBinding
    val permissionList = arrayOf(
        Manifest.permission.READ_EXTERNAL_STORAGE,
        Manifest.permission.ACCESS_MEDIA_LOCATION,
        Manifest.permission.ACCESS_BACKGROUND_LOCATION,
        Manifest.permission.INTERNET
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        requestPermissions(permissionList, 0)
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