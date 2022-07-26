package com.redhunter.searchfriends

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.redhunter.searchfriends.ui.home.HomeActivity
import com.redhunter.searchfriends.ui.login.LoginActivity

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Thread.sleep(1000)
        startActivity(Intent(this, HomeActivity::class.java))

    }
}