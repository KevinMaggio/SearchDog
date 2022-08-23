package com.redhunter.searchfriends.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.redhunter.searchfriends.R
import com.redhunter.searchfriends.databinding.FragmentHomeBinding
import com.redhunter.searchfriends.model.dto.retrofitDto.Status
import com.redhunter.searchfriends.ui.home.adapter.DogAdapter
import com.redhunter.searchfriends.utils.Connection
import com.redhunter.searchfriends.utils.Constants.SELECTED_IMAGE
import com.redhunter.searchfriends.utils.Constants.USER_PERMITS
import com.redhunter.searchfriends.utils.Permission
import com.redhunter.searchfriends.viewModel.dog.DogViewModel
import com.redhunter.searchfriends.viewModel.dog.DogViewModelFactory

class HomeFragment : Fragment() {

    private lateinit var dogViewModel: DogViewModel
    lateinit var binding: FragmentHomeBinding
    private var listDogs = mutableListOf<String>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        controlState(Status.LOADING)
        viewModelProvider()
        actions()
        calls()
        observers()

        return binding.root
    }

    private fun calls() {
        if (Connection.isOnline(requireContext())) {
            dogViewModel.getAllDogRandom(USER_PERMITS)
        } else {
            controlState(Status.ERROR)
        }
    }

    private fun observers() {
        dogViewModel.allDogRandomData.observe(viewLifecycleOwner, {
            listDogs = it.listDogs.toMutableList()
            controlState(it.status)
            initRecyclerView(listDogs)
        })
    }

    private fun controlState(state: Status) {
        when (state) {
            Status.ERROR -> {
                binding.noConection.root.visibility = View.VISIBLE
                binding.loading.root.visibility = View.GONE
            }
            Status.LOADING -> {
                binding.noConection.root.visibility = View.GONE
                binding.loading.root.visibility = View.VISIBLE
            }
            Status.SUCCESS -> {
                binding.noConection.root.visibility = View.GONE
                binding.loading.root.visibility = View.GONE
            }
        }
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
        SELECTED_IMAGE=image
        findNavController().navigate(R.id.action_nav_home_to_detailsFragment)
    }

    private fun onClickRemove(position: Int) {
        listDogs.removeAt(position)
        binding.rvDogs.adapter?.notifyItemRemoved(position)
    }


    private fun actions() {
        binding.btRandom.setOnClickListener {
            if (USER_PERMITS == Permission.COMPLETE) {
                findNavController().navigate(R.id.action_nav_home_to_randomDogFragment)
            } else {
                Toast.makeText(
                    context,
                    "Contenido no Disponible para Invitados",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
        binding.btDogs.setOnClickListener {
            if (USER_PERMITS == Permission.COMPLETE) {
                findNavController().navigate(R.id.action_nav_home_to_allDogsFragment)
            } else {
                Toast.makeText(
                    context,
                    "Contenido no Disponible para Invitados",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
        binding.btUs.setOnClickListener {
            findNavController().navigate(R.id.action_nav_home_to_usFragment)
        }
        binding.btSearch.setOnClickListener {
            if (USER_PERMITS == Permission.COMPLETE) {
                findNavController().navigate(R.id.action_nav_home_to_searchDogsFragment)
            } else {
                Toast.makeText(
                    context,
                    "Contenido no Disponible para Invitados",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
        binding.noConection.reload.setOnClickListener {
            controlState(Status.LOADING)
            calls()
        }

    }

    private fun viewModelProvider() {
        dogViewModel =
            DogViewModelFactory(requireActivity().application).create(DogViewModel::class.java)
    }
}