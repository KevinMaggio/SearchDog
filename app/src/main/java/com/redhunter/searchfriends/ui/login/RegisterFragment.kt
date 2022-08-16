package com.redhunter.searchfriends.ui.login

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.core.widget.doAfterTextChanged
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.redhunter.searchfriends.R
import com.redhunter.searchfriends.databinding.FragmentRegisterBinding
import com.redhunter.searchfriends.utils.StateLogin
import com.redhunter.searchfriends.viewModel.user.UserViewModel


class RegisterFragment : Fragment() {

    val userViewModel by activityViewModels<UserViewModel>()
    lateinit var binding: FragmentRegisterBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRegisterBinding.inflate(inflater, container, false)

        checkFields()
        actions()
        observers()

        return binding.root
    }

    private fun actions() {
        binding.btEnter.setOnClickListener {
            controlState(StateLogin.LOADING)
            userViewModel.registerUser(
                binding.etEmail.text.toString(),
                binding.etPassword.text.toString(),
                binding.etName.text.toString()
            )
        }
    }

    private fun observers() {
        userViewModel.getDataRegisterUser().observe(viewLifecycleOwner, {
            controlState(it)
        })

        userViewModel.validateFieldData.observe(viewLifecycleOwner, {
            controlButton(it)
        })
    }

    private fun controlState(state: StateLogin) {
        when (state) {
            StateLogin.ERROR -> {
                binding.itemLoading.root.isVisible = false
                Toast.makeText(context, "Email en uso ! ", Toast.LENGTH_LONG).show()
                cleanFields()
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
        binding.etName.text.clear()
        binding.etPassword.text.clear()
    }

    private fun checkFields() {
        binding.etEmail.doAfterTextChanged {
            userViewModel.validateFieldsRegister(
                it.toString(),
                binding.etPassword.text.toString(),
                binding.etName.text.toString()
            )
        }
        binding.etName.doAfterTextChanged {
            userViewModel.validateFieldsRegister(
                binding.etEmail.text.toString(),
                binding.etPassword.text.toString(),
                it.toString()
            )
        }
        binding.etPassword.doAfterTextChanged {
            userViewModel.validateFieldsRegister(
                binding.etEmail.text.toString(),
                it.toString(),
                binding.etName.text.toString()
            )
        }
    }


}