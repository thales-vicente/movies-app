package com.example.moviesapp.Adapters


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.moviesapp.Domain.SlidersItems
import com.example.moviesapp.R
import com.example.moviesapp.databinding.SlideItemContainerBinding
import java.util.Collections.addAll

class SlidersAdapter(
    private val items: List<SlidersItems>,
    val viewPager2: ViewPager2,
) : RecyclerView.Adapter<SlidersAdapter.SliderViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SliderViewHolder {
        val binding = SlideItemContainerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SliderViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SliderViewHolder, position: Int) {
        holder.setImage(items[position])
        if (position == items.size - 2) {
            viewPager2.post(runnable())
        }
    }

    override fun getItemCount(): Int = items.size

    class SliderViewHolder(private val binding: SlideItemContainerBinding) : RecyclerView.ViewHolder(binding.root) {
        val imageView = binding.ivSlide

        fun setImage(slidersItems: SlidersItems) {
            imageView.setImageDrawable(ContextCompat.getDrawable(binding.root.context,slidersItems.image))
        }
    }

    private fun runnable(): Runnable {
        return Runnable {
            addAll(arrayListOf(items))
        }
    }
}