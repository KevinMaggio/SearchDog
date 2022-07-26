package com.redhunter.searchfriends.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.redhunter.searchfriends.R
import com.redhunter.searchfriends.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    lateinit var binding: FragmentHomeBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding= FragmentHomeBinding.inflate(inflater, container, false)

        binding.btRandom.setOnClickListener {
            findNavController().navigate(R.id.action_nav_home_to_randomDogFragment)
        }

        return binding.root
    }
}