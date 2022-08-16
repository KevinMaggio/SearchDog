package com.redhunter.searchfriends.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.redhunter.searchfriends.R
import com.redhunter.searchfriends.databinding.FragmentHomeBinding
import com.redhunter.searchfriends.ui.home.adapter.DogAdapter
import com.redhunter.searchfriends.viewModel.dog.DogViewModel

class HomeFragment : Fragment() {

    private val dogViewModel by activityViewModels<DogViewModel>()
    lateinit var binding: FragmentHomeBinding
    private var listDogs=mutableListOf<String>()
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

    private fun calls() {
        dogViewModel.getAllDogRandom()
    }

    private fun observers() {
        dogViewModel.allDogRandomData.observe(viewLifecycleOwner, {
            listDogs= it.listDogs.toMutableList()
            initRecyclerView(listDogs)
        })
    }

    private fun removeWithSwiped() {
        val simpleCallBack = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.UP) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                onClickRemove(viewHolder.adapterPosition)
            }

        }

        val itemTouch = ItemTouchHelper(simpleCallBack)
        itemTouch.attachToRecyclerView(binding.rvDogs)
    }

    private fun initRecyclerView(list: List<String>) {
        removeWithSwiped()
        val adapter = DogAdapter(
            list,
            onclickListener = { onClickListener(it) })
        binding.rvDogs.adapter = adapter
    }

    private fun onClickListener(image: String) {
        val bundle= Bundle()
        bundle.putString("dog",image)
        findNavController().navigate(R.id.action_nav_home_to_detailsFragment,bundle)
    }

    private fun onClickRemove(position: Int) {
        listDogs.removeAt(position)
        binding.rvDogs.adapter?.notifyItemRemoved(position)
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