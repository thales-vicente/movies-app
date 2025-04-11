package com.example.moviesapp.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.moviesapp.Domian.SlidersItems
import com.example.moviesapp.databinding.SlideItemContainerBinding
import java.util.Collections.addAll

class SlidersAdapter(private val items: List<SlidersItems>, ViewPager2: ViewPager2) : RecyclerView.Adapter<SlidersAdapter.SliderViewHolder>() {
    inner class SliderViewHolder(private val binding: SlideItemContainerBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val imageView = binding.ivSlide
    }

    fun setImage(slidersItems: SlidersItems, context: Context, imageView: ImageView) {
        val requestOptions = RequestOptions()
        requestOptions.transforms(CenterCrop(), RoundedCorners(60))
        Glide.with(context)
            .load(slidersItems.getImage())
            .apply(requestOptions)
            .into(imageView)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SlidersAdapter.SliderViewHolder {
        val binding = SlideItemContainerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SliderViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SlidersAdapter.SliderViewHolder, position: Int) {
        setImage(slidersItems = )
        if (position == )
    }

    override fun getItemCount(): Int = items.size
}
private fun Runable(slidersItems: SlidersItems): Runnable{
    run (){
        addAll(arrayListOf(slidersItems))
    }
    return TODO("Provide the return value")
}