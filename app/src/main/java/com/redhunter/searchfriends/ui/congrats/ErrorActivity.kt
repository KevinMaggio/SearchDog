package com.redhunter.searchfriends.ui.congrats

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.redhunter.searchfriends.databinding.ActivityErrorBinding
import com.redhunter.searchfriends.ui.home.HomeActivity

class ErrorActivity : AppCompatActivity() {
    lateinit var binding: ActivityErrorBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityErrorBinding.inflate(layoutInflater)
        setContentView(binding.root)

        actions()
    }

    private fun actions() {
        binding.btBackHome.setOnClickListener {
            startActivity(Intent(this, HomeActivity::class.java))
            finish()
        }
    }
}