package com.redhunter.searchfriends.ui.home

import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.BounceInterpolator
import android.view.inputmethod.InputMethodManager
import android.widget.*
import androidx.core.animation.doOnEnd
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.redhunter.searchfriends.R
import com.redhunter.searchfriends.databinding.FragmentSearchDogsBinding
import com.redhunter.searchfriends.model.dto.retrofitDto.Status
import com.redhunter.searchfriends.ui.login.LoginActivity
import com.redhunter.searchfriends.utils.Connection
import com.redhunter.searchfriends.utils.Constants
import com.redhunter.searchfriends.utils.Constants.ALL_BREED
import com.redhunter.searchfriends.viewModel.dog.DogViewModel
import com.redhunter.searchfriends.viewModel.dog.DogViewModelFactory


class SearchDogsFragment : Fragment() {

    private lateinit var binding: FragmentSearchDogsBinding
    private lateinit var dogViewModel: DogViewModel
    private var breeds: MutableList<String> = mutableListOf()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchDogsBinding.inflate(inflater, container, false)

        selectOptionSpinner()
        setSpinner()
        viewModelProvider()
        calls(ALL_BREED.random())
        observers()
        actions()
        return binding.root
    }

    private fun calls(breed:String) {
        if (Connection.isOnline(requireContext())) {
            if (breed != "All Breeds") {
                dogViewModel.searchDog(breed)
            }
        } else {
            controlState(Status.ERROR)
        }
    }

    private fun observers() {
        dogViewModel.singleDogData.observe(viewLifecycleOwner, {
                controlState(Status.SUCCESS)
                setImage(it.dog)
                Constants.SELECTED_IMAGE = it.dog
        })
    }

    private fun setImage(image: String) {
        Glide.with(binding.ivDog).load(image).into(binding.ivDog)
    }

    private fun actions() {
        search()
        binding.btBackBlack.setOnClickListener {
            animateBack()
        }
        binding.btBackWhite.setOnClickListener {
            animateBack()
        }
        binding.ivBack.setOnClickListener {
            animateBack()
        }
        binding.ivDog.setOnClickListener {
            findNavController().navigate(R.id.action_searchDogsFragment_to_detailsFragment)
        }
        binding.noConection.reload.setOnClickListener {
            calls(ALL_BREED.random())
        }

    }

    private fun setSpinner(){
        breeds = ALL_BREED as MutableList<String>
        binding.noSearch.spOptions.adapter= ArrayAdapter(requireContext(),android.R.layout.simple_spinner_item,breeds)
    }

    private fun selectOptionSpinner(){
        binding.noSearch.spOptions.onItemSelectedListener =( object : androidx.appcompat.widget.AppCompatSpinner(requireContext()),
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                calls(breeds[position])
                binding.svBreed.setQuery("",false)
                hideKeyboard()

            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        })
    }

    private fun search() {
        binding.svBreed.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let {
                    binding.noSearch.spOptions.adapter= ArrayAdapter(requireContext(),android.R.layout.simple_spinner_item,breeds)
                    if (ALL_BREED.contains(query.lowercase())){
                    calls(it.lowercase())
                        binding.svBreed.setQuery("",false)
                    }else{
                        controlState(Status.NO_SEARCH)
                        binding.svBreed.setQuery("",false)
                    }
                }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
    }

    private fun controlState(state: Status) {
        when (state) {
            Status.ERROR -> {
                binding.noConection.root.visibility = View.VISIBLE
                binding.loading.root.visibility = View.GONE
                binding.cardView.visibility = View.GONE
                binding.noSearch.root.visibility = View.GONE
            }
            Status.LOADING -> {
                binding.noConection.root.visibility = View.GONE
                binding.loading.root.visibility = View.VISIBLE
                binding.cardView.visibility = View.GONE
                binding.noSearch.root.visibility = View.GONE
            }
            Status.SUCCESS -> {
                binding.noConection.root.visibility = View.GONE
                binding.loading.root.visibility = View.GONE
                binding.cardView.visibility = View.VISIBLE
                binding.noSearch.root.visibility = View.GONE
            }

            Status.NO_SEARCH ->{
                binding.noConection.root.visibility = View.GONE
                binding.loading.root.visibility = View.GONE
                binding.cardView.visibility = View.GONE
                binding.noSearch.root.visibility = View.VISIBLE
            }
        }
    }

    private fun viewModelProvider() {
        dogViewModel =
            DogViewModelFactory(requireActivity().application).create(DogViewModel::class.java)
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

    //3 method for hide keyboard
    private fun SearchDogsFragment.hideKeyboard() {
        view?.let { activity?.hideKeyboard(it) }
    }

    private fun LoginActivity.hideKeyboard() {
        hideKeyboard(currentFocus ?: View(this))
    }

    @SuppressLint("ServiceCast")
    private fun Context.hideKeyboard(view: View) {
        val inputMethodManager =
            getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }
}