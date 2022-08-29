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
import com.redhunter.searchfriends.databinding.FragmentRandomDogBinding
import com.redhunter.searchfriends.model.dto.retrofitDto.Status
import com.redhunter.searchfriends.utils.Connection
import com.redhunter.searchfriends.utils.Constants.SELECTED_IMAGE
import com.redhunter.searchfriends.viewModel.dog.DogViewModel
import com.redhunter.searchfriends.viewModel.dog.DogViewModelFactory


class RandomDogFragment : Fragment() {

    lateinit var binding: FragmentRandomDogBinding
    private lateinit var dogViewModel: DogViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRandomDogBinding.inflate(inflater, container, false)

        controlState(Status.LOADING)
        viewModelProvider()
        actions()
        calls()
        observers()

        return binding.root
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

    private fun actions() {
        binding.btBackBlack.setOnClickListener {
            animateBack()
        }
        binding.ivRandom.setOnClickListener {
            animate()
            if (Connection.isOnline(requireContext())) {
                dogViewModel.getOneDog()
            }
        }
        binding.noConection.reload.setOnClickListener {
            calls()
        }
        binding.ivDog.setOnClickListener {
            findNavController().navigate(R.id.action_randomDogFragment_to_detailsFragment)
        }
        binding.btBackWhite.setOnClickListener {
            animateBack()
        }
        binding.ivBack.setOnClickListener {
            animateBack()
        }
    }

    private fun viewModelProvider() {
        dogViewModel =
            DogViewModelFactory(requireActivity().application).create(DogViewModel::class.java)
    }

    private fun calls() {
        if (Connection.isOnline(requireContext())) {
            dogViewModel.getOneDog()
        } else {
            controlState(Status.ERROR)
        }
    }

    private fun observers() {
        dogViewModel.singleDogData.observe(viewLifecycleOwner, {
            if (it.status == "success") {
                controlState(Status.SUCCESS)
                setImage(it.dog)
                SELECTED_IMAGE = it.dog
            } else {
                controlState(Status.ERROR)
            }
        })
    }

    private fun setImage(image: String) {
        Glide.with(binding.ivDog).load(image).into(binding.ivDog)
    }


    private fun controlState(state: Status) {
        when (state) {
            Status.ERROR -> {
                binding.noConection.root.visibility = View.VISIBLE
                binding.loading.root.visibility = View.GONE
                binding.cardView.visibility = View.GONE
            }
            Status.LOADING -> {
                binding.noConection.root.visibility = View.GONE
                binding.loading.root.visibility = View.VISIBLE
                binding.cardView.visibility = View.GONE
            }
            Status.SUCCESS -> {
                binding.noConection.root.visibility = View.GONE
                binding.loading.root.visibility = View.GONE
                binding.cardView.visibility = View.VISIBLE
            }
        }
    }

    private fun animate() {
        binding.ivRandom.animate().apply {
            duration = 500
            rotationYBy(360f)
        }.start()
    }
}