package com.redhunter.searchfriends.ui.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.redhunter.searchfriends.R
import com.redhunter.searchfriends.databinding.FragmentRegisterBinding
import com.redhunter.searchfriends.viewModel.user.UserViewModel


class RegisterFragment : Fragment() {

    val userViewModel by activityViewModels<UserViewModel>()
    lateinit var binding: FragmentRegisterBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRegisterBinding.inflate(inflater, container, false)

        actions()
        observers()

        return binding.root
    }

    fun actions(){
        binding.btEnter.setOnClickListener {
            userViewModel.registerUser(
                binding.etEmail.text.toString(),
                binding.etPassword.text.toString(),
                binding.etName.text.toString()
            )
        }

    }

    fun observers(){
        userViewModel.getDataRegisterUser().observe(viewLifecycleOwner,{
            if (it.response){
                findNavController().navigate(R.id.action_registerFragment_to_registerSuccessFragment)
            }
        })
    }
}