package com.redhunter.searchfriends

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.redhunter.searchfriends.AppSearchFriends.Companion.preferences
import com.redhunter.searchfriends.ui.home.HomeActivity
import com.redhunter.searchfriends.ui.login.LoginActivity
import com.redhunter.searchfriends.ui.onboarding.OnboardingActivity
import com.redhunter.searchfriends.utils.Constants
import com.redhunter.searchfriends.utils.Constants.USER_NAME
import com.redhunter.searchfriends.utils.Constants.USER_PERMITS
import com.redhunter.searchfriends.utils.Permission


class SplashActivity : AppCompatActivity() {
    private val GOOGLE_SIGN = 120
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        checkLogin()
    }


    private fun checkLogin() {
        if (preferences.getUserEmail().isEmpty()) {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        } else {
            when (preferences.getUserEmail()) {
                "google" -> {
                    loginToGoogle()
                }
                "fireStore" -> {
                    USER_PERMITS = Permission.COMPLETE
                    USER_NAME= preferences.getFireBaseAuth()
                    startActivity(Intent(this, HomeActivity::class.java))
                    finish()
                }
                "invited" -> {
                    USER_PERMITS = Permission.LIMITED
                    startActivity(Intent(this, HomeActivity::class.java))
                    finish()
                }
            }
        }
    }

    private fun loginToGoogle() {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken("767235208299-53oiulf4e75isqsh2mqai6l5qv3m1dvq.apps.googleusercontent.com")
            .requestEmail()
            .build()
        val googleSignInClient = GoogleSignIn.getClient(this, gso)
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

                                USER_NAME = it.result.user?.email.toString()
                                USER_PERMITS = Permission.COMPLETE
                                startActivity(Intent(this, HomeActivity::class.java))
                                finish()

                            } else {
                                startActivity(Intent(this, LoginActivity::class.java))
                                finish()
                            }
                        }
                }
            } catch (e: Exception) {
                Log.d("error", "Internet Conection")
            }
        }
    }

}