package com.example.moviesapp.Adapters

import android.view.LayoutInflater
import android.view.RoundedCorner
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.moviesapp.Domian.SlidersItems
import com.example.moviesapp.databinding.SlideItemContainerBinding

class SlidersAdapter() : RecyclerView.Adapter<SlidersAdapter.SliderViewHolder>() {
    class SliderViewHolder(private val binding: SlideItemContainerBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val imageView = binding.ivSlide
    }

    fun setImage(slidersItems: SlidersItems) {
        val requestOptions = RequestOptions()
        requestOptions.transforms(CenterCrop(), RoundedCorners(60))
    }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SlidersAdapter.SliderViewHolder {
        val binding =
            SlideItemContainerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SliderViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SlidersAdapter.SliderViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

}