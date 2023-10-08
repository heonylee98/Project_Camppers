package com.heonylee98.camppers.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.heonylee98.camppers.MainActivity
import com.heonylee98.camppers.R
import com.heonylee98.camppers.databinding.FragmentMypageBinding

class MypageFragment : Fragment() {
    lateinit var fragmetMypageBinding: FragmentMypageBinding
    lateinit var navController: NavController

    private lateinit var auth: FirebaseAuth
    private lateinit var mGoogleSignInClient: GoogleSignInClient

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmetMypageBinding = FragmentMypageBinding.inflate(layoutInflater)
        navController = findNavController()

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
        userIdInit(auth.currentUser)
    }
    private fun userLogoutButtonPressed() {
        fragmetMypageBinding.btnMypageLogout.setOnClickListener {
            mGoogleSignInClient.signOut()
            auth.signOut()
            Snackbar.make(requireView(), "로그아웃 완료.", Snackbar.LENGTH_SHORT).show()

            // 아래 코드 실행 시 기존 navController에서 벗어나지않아 bottom navigation도 동시에 보이게됨 사용x
            // navController 자체를 login navigation으로 변경할 방법 찾기
            // navController.navigate(R.id.fragment_navigation_graph_xml)
        }
    }

    // firebase auth를 통해 넘겨받은 데이터 처리
    // mvvm을 통해 postUpload에서 같이 활용할 수 있게 변경 예정
    private fun userIdInit(user: FirebaseUser?) {
        fragmetMypageBinding.run {
            textMypageNickname.text = user?.displayName
            Glide.with(this@MypageFragment)
                .load(user?.photoUrl)
                .into(imageMypage)
            textMypageEmail.text = user?.email
        }
    }

}