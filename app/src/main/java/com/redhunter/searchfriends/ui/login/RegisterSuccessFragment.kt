package com.redhunter.searchfriends.ui.login

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.redhunter.searchfriends.R
import com.redhunter.searchfriends.databinding.FragmentRegisterSuccessBinding
import com.redhunter.searchfriends.ui.home.HomeActivity
import com.redhunter.searchfriends.ui.onboarding.OnboardingActivity


class RegisterSuccessFragment : Fragment() {
    lateinit var binding: FragmentRegisterSuccessBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
       binding= FragmentRegisterSuccessBinding.inflate(inflater, container, false)

        actions()
        return binding.root
    }

    private fun actions(){
        binding.root.setOnClickListener {
            startActivity(Intent(context, OnboardingActivity::class.java))
        }
    }

}