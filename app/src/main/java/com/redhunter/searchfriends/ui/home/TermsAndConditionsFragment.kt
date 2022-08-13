package com.redhunter.searchfriends.ui.home


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.redhunter.searchfriends.R
import com.redhunter.searchfriends.databinding.FragmentTemsAndConditionsBinding

class TermsAndConditionsFragment : Fragment() {
    lateinit var binding: FragmentTemsAndConditionsBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding= FragmentTemsAndConditionsBinding.inflate(inflater, container, false)


        return binding.root
    }

}