package com.redhunter.searchfriends.ui.home

import android.animation.ObjectAnimator
import android.content.Intent
import android.graphics.Path
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.BounceInterpolator
import android.view.animation.OvershootInterpolator
import androidx.core.animation.addListener
import androidx.core.animation.doOnEnd
import androidx.navigation.fragment.findNavController
import com.redhunter.searchfriends.R
import com.redhunter.searchfriends.databinding.FragmentRandomDogBinding


class RandomDogFragment : Fragment() {

    lateinit var binding: FragmentRandomDogBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRandomDogBinding.inflate(inflater, container, false)

        binding.btBackBlack.setOnClickListener {
            animateBack()
        }

        return binding.root
    }


    private fun animateBack() {
        val animator = ObjectAnimator.ofFloat(
            binding.btBackBlack,
            "translationX",
            (binding.btBackBlack.width.toFloat() - (binding.btBackBlack.width * 0.35).toFloat()))
                    animator.interpolator = BounceInterpolator ()
                    animator.setDuration (1500)
                    animator.start ()
                    animator.doOnEnd { findNavController().popBackStack() }
    }

}