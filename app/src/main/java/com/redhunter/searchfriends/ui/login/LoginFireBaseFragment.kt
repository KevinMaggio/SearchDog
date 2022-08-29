package com.redhunter.searchfriends.ui.login

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.redhunter.searchfriends.AppSearchFriends.Companion.preferences
import com.redhunter.searchfriends.R
import com.redhunter.searchfriends.databinding.FragmentLoginFireBaseBinding
import com.redhunter.searchfriends.ui.onboarding.OnboardingActivity
import com.redhunter.searchfriends.utils.Constants
import com.redhunter.searchfriends.utils.Constants.USER_NAME
import com.redhunter.searchfriends.utils.Permission
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

        controlButton(false)
        checkFields()
        actions()
        observers()

        return binding.root
    }

    private fun actions() {

        binding.btEnter.setOnClickListener {
            controlState(StateLogin.LOADING)
            userViewModel.loginFireStore(
                binding.etEmail.text.toString(),
                binding.etPassword.text.toString()
            )
            USER_NAME= binding.etEmail.text.toString()
            preferences.saveFireBaseAuth(binding.etEmail.text.toString())
        }
        binding.btBack.setOnClickListener {
            findNavController().popBackStack()
        }
        binding.modal.btRegister.setOnClickListener {
            findNavController().navigate(R.id.action_loginFireBaseFragment_to_registerFragment)
            userViewModel.loginFireStore("empty", "asd")
            cleanFields()
            binding.modal.root.isVisible = false
        }
        binding.modal.btCancel.setOnClickListener {
            userViewModel.loginFireStore("empty", "asd")
            cleanFields()
            binding.modal.root.isVisible = false
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
            StateLogin.NO_REGISTER -> {
                binding.itemLoading.root.isVisible = false
                showModal()
            }
            StateLogin.EMPTY -> {
                binding.itemLoading.root.isVisible = false
            }
            StateLogin.ERROR_PASS -> {
                binding.itemLoading.root.isVisible = false
                Toast.makeText(context, "Password error", Toast.LENGTH_LONG).show()
            }
            StateLogin.LOADING -> {
                binding.itemLoading.root.isVisible = true
            }
            StateLogin.SUCCESS -> {
                binding.itemLoading.root.isVisible = false
                Constants.USER_PERMITS = Permission.COMPLETE
                preferences.saveUserEmail("fireStore")
                startActivity(Intent(context, OnboardingActivity::class.java))
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


    private fun showModal() {
        binding.modal.root.isVisible = true
        hideKeyboard()
    }

    //3 method for hide keyboard
    private fun LoginFireBaseFragment.hideKeyboard() {
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