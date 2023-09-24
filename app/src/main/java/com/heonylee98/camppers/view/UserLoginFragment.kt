package com.heonylee98.camppers.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.heonylee98.camppers.databinding.FragmentUserLoginBinding

class UserLoginFragment : Fragment() {
    lateinit var fragmentUserLoginBinding: FragmentUserLoginBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentUserLoginBinding = FragmentUserLoginBinding.inflate(layoutInflater)
        googleLoginPressed()

        return fragmentUserLoginBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    private fun googleLoginPressed() {
        fragmentUserLoginBinding.googleLoginButton.setOnClickListener {
            Log.d("!","google login button pressed")
        }
    }
}