package com.redhunter.searchfriends.ui.onboarding

import android.app.ActivityOptions
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.transition.Explode
import androidx.core.view.isVisible
import com.redhunter.searchfriends.databinding.ActivityOnboardingBinding
import com.redhunter.searchfriends.ui.home.HomeActivity
import com.redhunter.searchfriends.viewModel.dog.DogViewModel
import com.redhunter.searchfriends.viewModel.dog.DogViewModelFactory
import kotlinx.coroutines.awaitAll


class OnboardingActivity : AppCompatActivity() {
    lateinit var binding: ActivityOnboardingBinding
    lateinit var dogViewModel: DogViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityOnboardingBinding.inflate(layoutInflater)
        setContentView(binding.root)


        providerViewModel()
        transitions()
        actions()
    }

    private fun actions(){
        binding.ivAccept.setOnClickListener {
            loading()
            val startIntent = Intent(this,HomeActivity::class.java)
            startActivity(startIntent,
                ActivityOptions.makeSceneTransitionAnimation(this).toBundle())
                this.finish()
        }
        dogViewModel.postDogsToBBDD()
    }

    private fun transitions(){
        val explode = Explode()
        explode.duration = 1000
        window.exitTransition = explode
    }

    private fun loading(){
        binding.progress.isVisible= true

    }

    private fun providerViewModel(){
        dogViewModel= DogViewModelFactory(this.application).create(DogViewModel::class.java)
    }
}