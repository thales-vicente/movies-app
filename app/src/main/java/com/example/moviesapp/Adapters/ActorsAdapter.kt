package com.example.moviesapp.Adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.moviesapp.databinding.ViewholderActorsBinding

class ActorsAdapter(private val images: List<String>): RecyclerView.Adapter<ActorsAdapter.ViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val binding = ViewholderActorsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindImage(images[position])
    }

    override fun getItemCount(): Int {
        return images.size
    }
    class ViewHolder(private val binding: ViewholderActorsBinding): RecyclerView.ViewHolder(binding.root){
        fun bindImage(images: String){
            Glide.with(binding.root.context)
                .load(images)
                .into(binding.itemImages)
        }
    }
}