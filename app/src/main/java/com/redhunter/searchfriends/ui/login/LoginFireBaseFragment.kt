package com.redhunter.searchfriends.ui.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.redhunter.searchfriends.R
import com.redhunter.searchfriends.databinding.FragmentLoginFireBaseBinding
import com.redhunter.searchfriends.utils.StateLogin
import com.redhunter.searchfriends.viewModel.user.UserViewModel


class LoginFireBaseFragment : Fragment() {
    private val userViewModel by activityViewModels<UserViewModel>()
    lateinit var binding: FragmentLoginFireBaseBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginFireBaseBinding.inflate(inflater, container, false)

        checkFields()
        actions()
        observers()

        return binding.root
    }

    private fun actions() {

        binding.btEnter.setOnClickListener {
            controlState(StateLogin.LOADING)
            userViewModel.loginFireStore(binding.etEmail.text.toString(),binding.etPassword.text.toString())
        }
    }

    private fun observers(){
        userViewModel.getDataRegisterUser().observe(viewLifecycleOwner,{
            controlState(it)
        })

        userViewModel.validateFieldData.observe(viewLifecycleOwner,{
            controlButton(it)
        })
    }

    private fun controlState(state: StateLogin) {
        when (state) {
            StateLogin.ERROR -> {
                binding.itemLoading.root.isVisible = false
                // activar modal
                Toast.makeText(context, "Crear Modal", Toast.LENGTH_LONG).show()
                cleanFields()
            }
            StateLogin.ERROR_PASS -> {
                binding.itemLoading.root.isVisible = false
                Toast.makeText(context, "Password incorrecta", Toast.LENGTH_LONG).show()
            }
            StateLogin.LOADING -> {
                binding.itemLoading.root.isVisible = true
            }
            StateLogin.SUCCESS -> {
                binding.itemLoading.root.isVisible = false
                findNavController().navigate(R.id.action_registerFragment_to_registerSuccessFragment)
            }
        }
    }

    private fun controlButton(state: Boolean) {
        binding.btEnter.isEnabled = state
    }

    private fun cleanFields() {
        binding.etEmail.text.clear()
        binding.etPassword.text.clear()
    }

    private fun checkFields() {
        binding.etEmail.doAfterTextChanged {
            userViewModel.validateFieldsLogin(
                it.toString(),
                binding.etPassword.text.toString(),
            )
        }
        binding.etPassword.doAfterTextChanged {
            userViewModel.validateFieldsLogin(
                binding.etEmail.text.toString(),
                it.toString(),
            )
        }
    }


}