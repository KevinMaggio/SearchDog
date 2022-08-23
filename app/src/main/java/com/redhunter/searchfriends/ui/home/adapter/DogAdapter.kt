package com.redhunter.searchfriends.ui.home.adapter

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.redhunter.searchfriends.R
import com.redhunter.searchfriends.databinding.ItemDogBinding
import java.net.URL

class DogAdapter(private val list: List<String>, val onclickListener: (String) -> Unit) : RecyclerView.Adapter<DogHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DogHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_dog, parent, false)
        return DogHolder(view)
    }

    override fun onBindViewHolder(holder: DogHolder, position: Int) {
        holder.render(list[position], onclickListener)
    }

    override fun getItemCount(): Int {
        return list.size
    }
}

class DogHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val binding = ItemDogBinding.bind(view)

    fun render(url: String,onclickListener: (String) -> Unit ){
        Glide.with(binding.itemImgDog).load(url).into(binding.itemImgDog)
        binding.itemImgDog.setOnClickListener { onclickListener(url) }
    }

}