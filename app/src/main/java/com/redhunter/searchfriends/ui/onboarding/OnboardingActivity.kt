package com.redhunter.searchfriends.ui.onboarding

import android.app.ActivityOptions
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.transition.Explode
import com.redhunter.searchfriends.databinding.ActivityOnboardingBinding
import com.redhunter.searchfriends.ui.home.HomeActivity


class OnboardingActivity : AppCompatActivity() {
    lateinit var binding: ActivityOnboardingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityOnboardingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        transitions()
        actions()

    }

    private fun actions(){
        binding.ivAccept.setOnClickListener {
            val startIntent = Intent(this,HomeActivity::class.java)
            startActivity(startIntent,
                ActivityOptions.makeSceneTransitionAnimation(this).toBundle())
        }
    }

    private fun transitions(){
        val explode = Explode()
        explode.duration = 1000
        window.exitTransition = explode
    }
}