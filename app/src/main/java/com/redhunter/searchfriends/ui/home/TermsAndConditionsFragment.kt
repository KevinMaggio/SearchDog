package com.redhunter.searchfriends.ui.home


import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.BounceInterpolator
import androidx.core.animation.doOnEnd
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.redhunter.searchfriends.R
import com.redhunter.searchfriends.databinding.FragmentTemsAndConditionsBinding

class TermsAndConditionsFragment : Fragment() {
    lateinit var binding: FragmentTemsAndConditionsBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTemsAndConditionsBinding.inflate(inflater, container, false)

        actions()

        return binding.root
    }

    private fun actions() {
        binding.btBackBlack.setOnClickListener {
            animateBack()
        }
        binding.btBackWhite.setOnClickListener {
            animateBack()
        }
        binding.ivBack.setOnClickListener {
            animateBack()
        }
    }

    private fun animateBack() {
        val animator = ObjectAnimator.ofFloat(
            binding.btBackBlack,
            "translationX",
            (binding.btBackBlack.width.toFloat() - (binding.btBackBlack.width * 0.35).toFloat())
        )
        animator.interpolator = BounceInterpolator()
        animator.setDuration(1500)
        animator.start()
        animator.doOnEnd { findNavController().popBackStack() }
    }
}