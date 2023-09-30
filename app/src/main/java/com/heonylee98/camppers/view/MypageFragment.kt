package com.heonylee98.camppers.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.heonylee98.camppers.R
import com.heonylee98.camppers.databinding.FragmentMypageBinding

class MypageFragment : Fragment() {
    lateinit var fragmetMypageBinding: FragmentMypageBinding

    private lateinit var auth: FirebaseAuth
    private lateinit var mGoogleSignInClient: GoogleSignInClient

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmetMypageBinding = FragmentMypageBinding.inflate(layoutInflater)

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.client_id))
            .requestEmail()
            .build()

        mGoogleSignInClient = GoogleSignIn.getClient(requireActivity(), gso)
        auth = FirebaseAuth.getInstance()

        return fragmetMypageBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        userLogoutButtonPressed()
    }
    private fun userLogoutButtonPressed() {
        fragmetMypageBinding.btnMypageLogout.setOnClickListener {
            mGoogleSignInClient.signOut()
            auth.signOut()
        }
    }

}