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
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.redhunter.searchfriends.R
import com.redhunter.searchfriends.databinding.FragmentAllDogsBinding
import com.redhunter.searchfriends.model.dto.retrofitDto.Status
import com.redhunter.searchfriends.ui.home.adapter.DogGridAdapter
import com.redhunter.searchfriends.utils.Connection
import com.redhunter.searchfriends.utils.Constants.SELECTED_IMAGE
import com.redhunter.searchfriends.utils.Constants.USER_PERMITS
import com.redhunter.searchfriends.viewModel.dog.DogViewModel
import com.redhunter.searchfriends.viewModel.dog.DogViewModelFactory


class AllDogsFragment : Fragment() {

    lateinit var binding: FragmentAllDogsBinding
    lateinit var dogViewModel:DogViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding= FragmentAllDogsBinding.inflate(inflater, container, false)

        controlState(Status.LOADING)
        providerViewModel()
        actions()
        calls()
        observers()

        return binding.root
    }

    private fun actions(){
        binding.btBackBlack.setOnClickListener {
            animateBack()
        }
        binding.btBackWhite.setOnClickListener {
            animateBack()
        }
        binding.back.setOnClickListener {
            animateBack()
        }
        binding.ivRandom.setOnClickListener {
            animate()
            calls()
        }

    }

    private fun calls(){
        if (Connection.isOnline(requireContext())){
            dogViewModel.getAllDogRandom(USER_PERMITS)
        }else{
            controlState(Status.ERROR)
        }
    }

    private fun observers(){
        dogViewModel.allDogRandomData.observe(viewLifecycleOwner,{
            controlState(it.status)
            initRecyclerView(it.listDogs)
        })
    }

    private fun initRecyclerView(list:List<String>){
        val adapter = DogGridAdapter(list, onclickListener = {onClick(it)})
        val grid = StaggeredGridLayoutManager(3, LinearLayoutManager.VERTICAL)
        binding.rvDogs.adapter= adapter
        binding.rvDogs.layoutManager= grid
    }

    private fun onClick(img:String){
        SELECTED_IMAGE =img
        findNavController().navigate(R.id.action_allDogsFragment_to_detailsFragment)
    }

    private fun controlState(state: Status) {
        when (state) {
            Status.ERROR -> {
                binding.noConection.root.visibility = View.VISIBLE
                binding.loading.root.visibility = View.GONE
                binding.rvDogs.visibility = View.GONE
            }
            Status.LOADING -> {
                binding.noConection.root.visibility = View.GONE
                binding.loading.root.visibility = View.VISIBLE
                binding.rvDogs.visibility = View.GONE
            }
            Status.SUCCESS -> {
                binding.noConection.root.visibility = View.GONE
                binding.loading.root.visibility = View.GONE
                binding.rvDogs.visibility = View.VISIBLE
            }
        }
    }

    private fun animate() {
        binding.ivRandom.animate().apply {
            duration = 500
            rotationYBy(360f)
        }.start()
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
    private fun providerViewModel(){
        dogViewModel= DogViewModelFactory(requireActivity().application).create(DogViewModel::class.java)
    }

}