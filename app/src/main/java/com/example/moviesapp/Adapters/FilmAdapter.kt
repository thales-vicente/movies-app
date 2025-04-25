package com.example.moviesapp.Adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.moviesapp.Activities.DetailActivity
import com.example.moviesapp.Domain.Daum
import com.example.moviesapp.Domain.Root
import com.example.moviesapp.databinding.ViewholderFilmBinding

class FilmAdapter(private val items: Root): RecyclerView.Adapter<FilmAdapter.ViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val binding = ViewholderFilmBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
        holder.bindingItem(items.data[position])
    }

    override fun getItemCount(): Int {
        return items.data.size
    }

    class ViewHolder(private val binding: ViewholderFilmBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bindingItem(item: Daum){
            binding.tvTitleText.text = item.title
            val requestOptions = RequestOptions()
            requestOptions.transforms(CenterCrop(), RoundedCorners(30))
            Glide.with(binding.root.context)
                .load(item.poster)
                .apply(requestOptions)
                .into(binding.ivPic)

            binding.root.setOnClickListener {
                val intent = Intent(binding.root.context, DetailActivity::class.java)
                binding.root.context.startActivity(intent)
            }
        }
    }
}