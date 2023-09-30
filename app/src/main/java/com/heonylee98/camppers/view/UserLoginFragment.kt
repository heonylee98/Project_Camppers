package com.heonylee98.camppers.view

import android.Manifest
import android.app.Activity
import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.heonylee98.camppers.MainActivity
import com.heonylee98.camppers.R
import com.heonylee98.camppers.databinding.FragmentUserLoginBinding

class UserLoginFragment : Fragment() {
    lateinit var fragmentUserLoginBinding: FragmentUserLoginBinding

    private lateinit var auth: FirebaseAuth
    private lateinit var mGoogleSignInClient: GoogleSignInClient
    private lateinit var startGoogleLoginForResult : ActivityResultLauncher<Intent>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentUserLoginBinding = FragmentUserLoginBinding.inflate(layoutInflater)

        userStateMethodWithGoogle()

        return fragmentUserLoginBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        googleLoginButtonPressed()
    }

    private fun userStateMethodWithGoogle() {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.client_id))
            .requestEmail()
            .build()

        mGoogleSignInClient = GoogleSignIn.getClient(requireActivity(), gso)
        auth = FirebaseAuth.getInstance()

        startGoogleLoginForResult =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode == Activity.RESULT_OK) {
                    result.data?.let { data ->
                        val task = GoogleSignIn.getSignedInAccountFromIntent(data)
                        val account = task.getResult(ApiException::class.java)!!

                        firebaseAuthWithGoogle(account.idToken!!)
                    }
                } else {
                    Snackbar.make(requireView(), "Login failed try it later", Snackbar.LENGTH_SHORT).show()
                }
            }
    }
    private fun firebaseAuthWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener(requireActivity()) { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    updateUI(user)
                } else {
                    updateUI(null)
                }
            }
    }
    private fun googleLoginButtonPressed() {
        fragmentUserLoginBinding.googleLoginButton.setOnClickListener {
            val signInIntent = mGoogleSignInClient.signInIntent
            startGoogleLoginForResult.launch(signInIntent)
        }
    }

    // homeFragment에서 updateUi메서드 작동하게 이전
    private fun updateUI(user: FirebaseUser?) {
        // uid와 일치하는 닉네임 정보 firestore db에서 받아서 보여주는 메서드 필요
        Snackbar.make(requireView(), "${user?.uid}님 환영합니다.", Snackbar.LENGTH_SHORT).show()
    }
}