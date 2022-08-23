package com.redhunter.searchfriends.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.redhunter.searchfriends.R
import com.redhunter.searchfriends.databinding.FragmentAdoptErrorBinding

class AdoptErrorFragment : Fragment() {

    lateinit var binding: FragmentAdoptErrorBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding= FragmentAdoptErrorBinding.inflate(inflater, container, false)
        actions()
        return binding.root
    }

    private fun actions(){
        binding.btBackHome.setOnClickListener {
            findNavController().navigate(R.id.action_adoptErrorFragment_to_nav_home)
        }
    }
}