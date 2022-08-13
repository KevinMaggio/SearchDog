package com.redhunter.searchfriends.ui.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.redhunter.searchfriends.R
import com.redhunter.searchfriends.databinding.ItemDogBinding
import com.squareup.picasso.Picasso

class DogAdapter(private val list: List<String>) : RecyclerView.Adapter<DogHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DogHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_dog, parent, false)
        return DogHolder(view)
    }

    override fun onBindViewHolder(holder: DogHolder, position: Int) {
        holder.render(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }
}

class DogHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val binding = ItemDogBinding.bind(view)

    fun render(url: String) {
        Picasso.get().load(url).into(binding.itemImgDog)
    }

}