package com.redhunter.searchfriends.ui.home

import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.BounceInterpolator
import androidx.core.animation.doOnEnd
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.redhunter.searchfriends.R
import com.redhunter.searchfriends.databinding.FragmentDetailsBinding
import com.redhunter.searchfriends.ui.congrats.ErrorActivity
import com.redhunter.searchfriends.ui.congrats.SuccessActivity
import com.redhunter.searchfriends.utils.Connection
import com.redhunter.searchfriends.utils.Constants.SELECTED_IMAGE

class DetailsFragment : Fragment() {

    lateinit var binding: FragmentDetailsBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailsBinding.inflate(inflater, container, false)

        binding.loading.pbLoading.visibility = View.GONE
        setImage()
        actions()
        return binding.root
    }

    private fun setImage() {
        Glide.with(binding.imgDog).load(SELECTED_IMAGE).into(binding.imgDog)
    }

    private fun actions() {
        binding.btBackBlack.setOnClickListener {
            animateBack()
        }
        binding.btFood.setOnClickListener {
            binding.tvText.text = getString(R.string.details_tv_description_food)
        }
        binding.btFriends.setOnClickListener {
            binding.tvText.text = getString(R.string.details_tv_description_friends)
        }
        binding.btHealth.setOnClickListener {
            binding.tvText.text = getString(R.string.details_tv_description_health)
        }
        binding.btHouse.setOnClickListener {
            binding.tvText.text = getString(R.string.details_tv_description_house)
        }
        binding.ivDog.setOnClickListener {
            if (Connection.isOnline(requireContext())) {
                startActivity(Intent(context, SuccessActivity::class.java))
                activity?.finish()
            } else {
                startActivity(Intent(context, ErrorActivity::class.java))
                activity?.finish()
            }
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