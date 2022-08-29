package com.redhunter.searchfriends.ui.home

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.redhunter.searchfriends.R
import com.redhunter.searchfriends.databinding.FragmentUsBinding
import com.redhunter.searchfriends.ui.congrats.ErrorActivity
import com.redhunter.searchfriends.ui.home.adapter.DogAdapter
import com.redhunter.searchfriends.utils.Connection


class UsFragment : Fragment() {

    lateinit var binding: FragmentUsBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentUsBinding.inflate(inflater, container, false)


        binding.ivZoom.visibility = View.GONE
        inflateView()
        actions()
        return binding.root
    }

    private fun inflateView() {
        if (Connection.isOnline(requireContext())) {
            initRecyclerViewAboutUs(getImage())
            initRecyclerViewMembers(getImageMembers())
        } else {
            startActivity(Intent(context, ErrorActivity::class.java))
        }
    }

    private fun initRecyclerViewMembers(list: List<String>) {
        val adapter = DogAdapter(list, onclickListener = { onClick(it) })
        binding.rvMembers.adapter = adapter
    }

    private fun initRecyclerViewAboutUs(list: List<String>) {
        val adapter = DogAdapter(list, onclickListener = { onClick(it) })
        binding.rvAboutUs.adapter = adapter
    }

    private fun onClick(img: String) {
        binding.ivZoom.visibility = View.VISIBLE
        setImage(img)
    }

    private fun setImage(url: String) {
        Glide.with(binding.ivZoom).load(url).into(binding.ivZoom)
    }

    private fun actions() {
        binding.btBack.setOnClickListener {
            findNavController().popBackStack()
        }
        binding.ivZoom.setOnClickListener {
            binding.ivZoom.visibility = View.GONE
        }
    }

    private fun getImage(): List<String> {
        return listOf(
            "https://i.ibb.co/6HDshHq/Whats-App-Image-2022-08-28-at-14-19-16-1.jpg",
            "https://i.ibb.co/1mVq7Lw/Whats-App-Image-2022-08-28-at-14-19-16.jpg",
            "https://i.ibb.co/SvKM0t3/Whats-App-Image-2022-08-28-at-14-19-15-1.jpg",
            "https://i.ibb.co/ftzL9f3/Whats-App-Image-2022-08-28-at-14-19-15.jpg",
            "https://i.ibb.co/x6B0wmt/Whats-App-Image-2022-08-28-at-14-19-14-1.jpg",
            "https://i.ibb.co/cYh7NmB/Whats-App-Image-2022-08-28-at-14-19-14.jpg",
            "https://i.ibb.co/BTxgyLp/Whats-App-Image-2022-08-28-at-14-19-13-1.jpg",
            "https://i.ibb.co/cr1wGSw/Whats-App-Image-2022-08-28-at-14-19-13.jpg",
            "https://i.ibb.co/r0HDrMj/Whats-App-Image-2022-08-28-at-14-19-12-1.jpg",
            "https://i.ibb.co/PN1N6rp/Whats-App-Image-2022-08-28-at-14-19-12.jpg",
            "https://i.ibb.co/wMTCSqp/Whats-App-Image-2022-04-22-at-11-34-06.jpg",
            "https://i.ibb.co/5kqZMpW/Whats-App-Image-2022-04-22-at-11-33-51.jpg",
            "https://i.ibb.co/cgDd2zp/Whats-App-Image-2022-04-22-at-11-33-03.jpg",
            "https://i.ibb.co/xz7dLjN/Whats-App-Image-2022-04-22-at-11-32-50.jpg",
            "https://i.ibb.co/n1WH7rx/Whats-App-Image-2022-04-22-at-11-32-39.jpg",
            "https://i.ibb.co/cvfcjvP/Whats-App-Image-2022-04-22-at-11-32-24.jpg",
            "https://i.ibb.co/6F0cRpK/Whats-App-Image-2022-04-22-at-11-31-29.jpg",
            "https://i.ibb.co/HrXrTzg/Whats-App-Image-2022-04-22-at-11-30-47.jpg",
            "https://i.ibb.co/gm3cmfT/Whats-App-Image-2022-04-22-at-11-30-31.jpg"
        )
    }

    private fun getImageMembers(): List<String> {
        return listOf(
            "https://i.ibb.co/XyR3RWs/foto5.png",
            "https://i.ibb.co/3Cv8HgT/foto4.png",
            "https://i.ibb.co/XtV81T6/foto3.png",
            "https://i.ibb.co/ZJ0QqHj/foto2.png",
            "https://i.ibb.co/ydMkDqt/foto1.png"
        )
    }
}