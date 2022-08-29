package com.redhunter.searchfriends.ui.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.redhunter.searchfriends.R
import com.redhunter.searchfriends.databinding.ItemGridDogBinding

class DogGridAdapter (private val list: List<String>, private val onclickListener: (String) -> Unit) : RecyclerView.Adapter<DogGridHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DogGridHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_grid_dog, parent, false)
        return DogGridHolder(view)
    }

    override fun onBindViewHolder(holder: DogGridHolder, position: Int) {
        holder.render(list[position], onclickListener)
    }

    override fun getItemCount(): Int {
        return list.size
    }
}

class DogGridHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val binding = ItemGridDogBinding.bind(view)

    fun render(url: String,onclickListener: (String) -> Unit ){
        Glide.with(binding.ivDog).load(url).into(binding.ivDog)
        binding.ivDog.setOnClickListener { onclickListener(url) }
    }

}