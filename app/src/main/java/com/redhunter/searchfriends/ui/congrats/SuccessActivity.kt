package com.redhunter.searchfriends.ui.congrats

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.findNavController
import com.redhunter.searchfriends.R
import com.redhunter.searchfriends.databinding.ActivitySuccessBinding
import com.redhunter.searchfriends.ui.home.HomeActivity

class SuccessActivity : AppCompatActivity() {
    lateinit var binding:ActivitySuccessBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivitySuccessBinding.inflate(layoutInflater)
        setContentView(binding.root)

        actions()

    }
    private fun actions(){
        binding.btBackHome.setOnClickListener {
            startActivity(Intent(this,HomeActivity::class.java))
            finish()
        }
    }
}