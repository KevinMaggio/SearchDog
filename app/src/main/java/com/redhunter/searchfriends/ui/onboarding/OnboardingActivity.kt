package com.redhunter.searchfriends.ui.onboarding

import android.animation.ObjectAnimator
import android.app.ActivityOptions
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.transition.Explode
import android.view.animation.BounceInterpolator
import androidx.core.animation.doOnEnd
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import com.redhunter.searchfriends.databinding.ActivityOnboardingBinding
import com.redhunter.searchfriends.ui.home.HomeActivity
import com.redhunter.searchfriends.viewModel.dog.DogViewModel
import com.redhunter.searchfriends.viewModel.dog.DogViewModelFactory
import kotlinx.coroutines.awaitAll
import android.animation.AnimatorListenerAdapter


class OnboardingActivity : AppCompatActivity() {
    lateinit var binding: ActivityOnboardingBinding
    lateinit var dogViewModel: DogViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnboardingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        animate()
        providerViewModel()
        actions()
    }

    private fun actions() {
        binding.ivAccept.setOnClickListener {
            startActivity( Intent(this, HomeActivity::class.java))
            finish()
        }
        dogViewModel.postDogsToBBDD()
    }

    private fun providerViewModel() {
        dogViewModel = DogViewModelFactory(this.application).create(DogViewModel::class.java)
    }

    private fun animate() {
        binding.indicator.animate().apply {
            duration = 500
            translationXBy(100f)
        }.withEndAction {
            binding.indicator.animate().apply {
                translationXBy(-100f)
            }.withEndAction {
                animate()
            }
        }
    }

}