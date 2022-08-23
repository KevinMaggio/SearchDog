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
import com.redhunter.searchfriends.utils.Constants
import com.redhunter.searchfriends.utils.Constants.SELECTED_IMAGE
import com.redhunter.searchfriends.viewModel.dog.DogViewModel
import com.redhunter.searchfriends.viewModel.dog.DogViewModelFactory


class RandomDogFragment : Fragment() {

    lateinit var binding: FragmentRandomDogBinding
    private lateinit var dogViewModel: DogViewModel
    private var position = 0
    private var listDog = listOf<String>()
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
            if (Connection.isOnline(requireContext())) {
                setImageRandom(listDog)
            }
        }
        binding.noConection.reload.setOnClickListener {
            calls()
        }
        binding.ivDog.setOnClickListener {
            SELECTED_IMAGE=listDog[position-1]
            findNavController().navigate(R.id.action_randomDogFragment_to_detailsFragment)
        }
    }

    private fun viewModelProvider() {
        dogViewModel =
            DogViewModelFactory(requireActivity().application).create(DogViewModel::class.java)
    }

    private fun calls() {
        if (Connection.isOnline(requireContext())) {
            dogViewModel.getAllDogRandom(Constants.USER_PERMITS)
        } else {
            controlState(Status.ERROR)
        }
    }

    private fun observers() {
        dogViewModel.allDogRandomData.observe(viewLifecycleOwner, {
            listDog = it.listDogs
            setImageRandom(listDog)
            controlState(it.status)
        })
    }

    private fun setImage(image: String) {
        Glide.with(binding.ivDog).load(image).into(binding.ivDog)
    }

    private fun setImageRandom(listImages: List<String>) {
        setImage(listImages[position])
        position += 1
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
}