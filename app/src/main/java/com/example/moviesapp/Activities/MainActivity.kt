package com.example.moviesapp.Activities


import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import com.example.moviesapp.Adapters.SlidersAdapter
import com.example.moviesapp.Domian.SlidersItems
import com.example.moviesapp.R
import com.example.moviesapp.databinding.ActivityMainBinding
import kotlinx.coroutines.Runnable
import kotlin.math.abs

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var ViewPager2 = binding.viewPager2
    private var slideHandler = Handler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
    private fun banners(slidersItems: SlidersItems){
        arrayListOf<SlidersItems>(
            slidersItems.add(SlidersItems(R.drawable.wide)),
            slidersItems.add(SlidersItems(R.drawable.wide1)),
            slidersItems.add(SlidersItems(R.drawable.wide3))
        )
        ViewPager2.adapter = SlidersAdapter(slidersItems, ViewPager2)
        ViewPager2.clipToPadding = false
        ViewPager2.offscreenPageLimit = 3
        ViewPager2.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_ALWAYS

        val compositePageTransform = CompositePageTransformer()
        compositePageTransform.addTransformer(MarginPageTransformer(40))
        compositePageTransform.addTransformer(ViewPager2.PageTransformer(){
            fun transformPage(page: View, position: Float){
                val r = 1-abs(position)
                page.scaleY(0.85+r*0.15f)
            }
        })
        ViewPager2.setPageTransformer(compositePageTransform)
        ViewPager2.currentItem = 1
        ViewPager2.registerOnPageChangeCallback(ViewPager2.OnPageChangeCallback(){
            fun onPageSelected(position: Int){
                slideHandler.removeCallbacks(sliderRunnable())
            }
        })
    }
    fun sliderRunnable(): Runnable{
            ViewPager2.currentItem = ViewPager2.currentItem + 1
        return TODO("Provide the return value")
    }

    override fun onPause() {
        super.onPause()
        slideHandler.removeCallbacks(sliderRunnable())
    }

    override fun onResume() {
        super.onResume()
        slideHandler.postDelayed(sliderRunnable(), 2000)
    }
}