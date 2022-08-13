package com.redhunter.searchfriends.ui.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.redhunter.searchfriends.R
import com.redhunter.searchfriends.databinding.FragmentLoginBinding
import com.redhunter.searchfriends.ui.onboarding.OnboardingActivity


class LoginFragment : Fragment() {
    lateinit var binding: FragmentLoginBinding
    private val GOOGLE_SIGN = 120

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(inflater, container, false)

        actions()

        return binding.root
    }

    private fun actions() {
        binding.btLogin.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_loginFireBaseFragment)
        }
        binding.btLoginGoogle.setOnClickListener {
            loginToGoogle()
        }
        binding.btRegister.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }
    }


    private fun loginToGoogle() {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken("767235208299-53oiulf4e75isqsh2mqai6l5qv3m1dvq.apps.googleusercontent.com")
            .requestEmail()
            .build()
        val googleSignInClient = GoogleSignIn.getClient(requireContext(), gso)
        startActivityForResult(googleSignInClient.signInIntent, GOOGLE_SIGN)
    }

    //conection whit Google Acount
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == GOOGLE_SIGN) {
            val tast = GoogleSignIn.getSignedInAccountFromIntent(data)

            try {
                val account = tast.getResult(ApiException::class.java)
                if (account != null) {
                    val credential = GoogleAuthProvider.getCredential(account.idToken, null)
                    FirebaseAuth.getInstance().signInWithCredential(credential)
                        .addOnCompleteListener {
                            if (it.isSuccessful) {
                                Log.d("success", account.email.toString())

                                startActivity(Intent(context, OnboardingActivity::class.java))
                                activity?.finish()

                            } else {
                                Toast.makeText(context, "Error login", Toast.LENGTH_LONG).show()
                            }
                        }
                }
            } catch (e: Exception) {
                Log.d("error", "Internet Conection")
            }
        }
    }


}