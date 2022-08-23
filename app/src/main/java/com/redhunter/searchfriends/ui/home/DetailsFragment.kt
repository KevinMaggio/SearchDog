package com.redhunter.searchfriends.ui.home

import android.animation.ObjectAnimator
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.BounceInterpolator
import androidx.core.animation.doOnEnd
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.redhunter.searchfriends.R
import com.redhunter.searchfriends.databinding.FragmentDetailsBinding
import com.redhunter.searchfriends.utils.Connection
import com.redhunter.searchfriends.utils.Constants.SELECTED_IMAGE

class DetailsFragment : Fragment() {

    lateinit var binding:FragmentDetailsBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding= FragmentDetailsBinding.inflate(inflater, container, false)

        setImage()
        actions()
        return binding.root
    }

    private fun setImage(){
        Glide.with(binding.imgDog).load(SELECTED_IMAGE).into(binding.imgDog)
    }

    private fun actions(){
        binding.btBackBlack.setOnClickListener {
            animateBack()
        }
        binding.btFood.setOnClickListener {
            binding.tvText.text=getString(R.string.details_tv_description_food)
        }
        binding.btFriends.setOnClickListener {
            binding.tvText.text=getString(R.string.details_tv_description_friends)
        }
        binding.btHealth.setOnClickListener {
            binding.tvText.text=getString(R.string.details_tv_description_health)
        }
        binding.btHouse.setOnClickListener {
            binding.tvText.text=getString(R.string.details_tv_description_house)
        }
        binding.ivDog.setOnClickListener {
            if(Connection.isOnline(requireContext())){
                findNavController().navigate(R.id.action_detailsFragment_to_adoptSuccessFragment)
            }else{
                findNavController().navigate(R.id.action_detailsFragment_to_adoptErrorFragment)
            }
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