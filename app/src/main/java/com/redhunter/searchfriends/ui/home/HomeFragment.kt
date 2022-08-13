package com.redhunter.searchfriends.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.redhunter.searchfriends.R
import com.redhunter.searchfriends.databinding.FragmentHomeBinding
import com.redhunter.searchfriends.ui.home.adapter.DogAdapter
import com.redhunter.searchfriends.viewModel.dog.DogViewModel

class HomeFragment : Fragment() {

    val dogViewModel by activityViewModels<DogViewModel>()
    lateinit var binding: FragmentHomeBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        actions()
        calls()
        observers()

        return binding.root
    }

    private fun calls(){
        dogViewModel.getAllDogRandom()
    }

    private fun observers(){
        dogViewModel.allDogRandomData.observe(viewLifecycleOwner,{
            initRecyclerView(it.listDogs)
        })
    }

    private fun initRecyclerView(list: List<String>){
        val adapter = DogAdapter(list)
        binding.rvDogs.adapter = adapter
    }


    private fun actions() {
        binding.btRandom.setOnClickListener {
            findNavController().navigate(R.id.action_nav_home_to_randomDogFragment)
        }
        binding.btDogs.setOnClickListener {
            findNavController().navigate(R.id.action_nav_home_to_allDogsFragment)
        }
        binding.btUs.setOnClickListener {
            findNavController().navigate(R.id.action_nav_home_to_usFragment)
        }
        binding.btSearch.setOnClickListener {
            findNavController().navigate(R.id.action_nav_home_to_searchDogsFragment)
        }

    }
}