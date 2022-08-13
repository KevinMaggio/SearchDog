package com.redhunter.searchfriends.ui.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import com.redhunter.searchfriends.databinding.FragmentLoginFireBaseBinding
import com.redhunter.searchfriends.viewModel.user.UserViewModel


class LoginFireBaseFragment : Fragment() {
    val userViewModel by activityViewModels<UserViewModel>()
    lateinit var binding: FragmentLoginFireBaseBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginFireBaseBinding.inflate(inflater, container, false)

        actions()
        observers()

        return binding.root
    }

    private fun actions() {
        userViewModel.getAllUser()
        binding.btEnter.setOnClickListener {
            //ejecutar el login
        }
    }

    private fun observers(){
        userViewModel.checkLoginUser().observe(viewLifecycleOwner,{
            Toast.makeText(context,it.toString(),Toast.LENGTH_LONG).show()
        })
    }


}