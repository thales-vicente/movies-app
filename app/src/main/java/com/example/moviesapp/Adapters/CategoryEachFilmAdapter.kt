package com.example.moviesapp.Adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.moviesapp.Domain.GenresItem
import com.example.moviesapp.databinding.ViewholderCategoryBinding

class CategoryEachFilmAdapter(private val items: List<String>): RecyclerView.Adapter<CategoryEachFilmAdapter.ViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val binding = ViewholderCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
        holder.bindingItem(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class ViewHolder(private val binding: ViewholderCategoryBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bindingItem(item: String){
            binding.tvTitleText.text = item

            binding.root.setOnClickListener {

            }
        }
    }
}