package com.example.moviesapp.Activities


import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.moviesapp.Adapters.CategoryAdapter
import com.example.moviesapp.Adapters.FilmAdapter
import com.example.moviesapp.Adapters.SlidersAdapter
import com.example.moviesapp.Domain.GenresItem
import com.example.moviesapp.Domain.Root
import com.example.moviesapp.Domain.SlidersItems
import com.example.moviesapp.R
import com.example.moviesapp.databinding.ActivityMainBinding
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.Runnable
import kotlin.math.abs

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    lateinit var viewPager2: ViewPager2
    private var slideHandler = Handler()
    private lateinit var adapterBestMovies: RecyclerView.Adapter<FilmAdapter.ViewHolder>
    private lateinit var adapterUpComing: RecyclerView.Adapter<FilmAdapter.ViewHolder>
    private lateinit var adapterCategory: RecyclerView.Adapter<CategoryAdapter.ViewHolder>
    private lateinit var recyclerViewBestMovies: RecyclerView
    private lateinit var recyclerViewUpcoming: RecyclerView
    private lateinit var recyclerViewCategory: RecyclerView
    private lateinit var loading1: ProgressBar
    private lateinit var loading2: ProgressBar
    private lateinit var loading3: ProgressBar
    private lateinit var mRequestQueue: RequestQueue
    private lateinit var mStringRequest: StringRequest
    private lateinit var mStringRequest2: StringRequest
    private lateinit var mStringRequest3: StringRequest


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewPager2 = binding.viewPager2
        recyclerViewBestMovies = binding.recyclerView1
        recyclerViewBestMovies.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        recyclerViewCategory = binding.recyclerView2
        recyclerViewCategory.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        recyclerViewUpcoming = binding.recyclerView3
        recyclerViewUpcoming.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        loading1 = binding.progressBar1
        loading2 = binding.progressBar2
        loading3 = binding.progressBar3

        banners()
        sendRequestBestMovies()
        sendRequestUpComing()
        sendRequestCategory()
    }

    private fun sendRequestBestMovies() {
        mRequestQueue = Volley.newRequestQueue(this)
        loading1.visibility = View.VISIBLE
        mStringRequest = StringRequest(
            Request.Method.GET,
            "https://moviesapi.ir/api/v1/movies?page=1",
            { response ->
                val gson = Gson()
                loading1.visibility = View.GONE
                val items: Root = gson.fromJson(response, Root::class.java)
                adapterBestMovies = FilmAdapter(items)
                recyclerViewBestMovies.adapter = adapterBestMovies
            }, {error ->
                loading1.visibility = View.GONE
                Log.i("UiLover", "onErrorResponse ${error.toString()}")
            })
        mRequestQueue.add(mStringRequest)
    }
    private fun sendRequestUpComing() {
        mRequestQueue = Volley.newRequestQueue(this)
        loading3.visibility = View.VISIBLE
        mStringRequest3 = StringRequest(
            Request.Method.GET,
            "https://moviesapi.ir/api/v1/movies?page=2",
            { response ->
                val gson = Gson()
                loading3.visibility = View.GONE
                val items: Root = gson.fromJson(response, Root::class.java)
                adapterUpComing = FilmAdapter(items)
                recyclerViewUpcoming.adapter = adapterUpComing
            }, {error ->
                loading3.visibility = View.GONE
                Log.i("UiLover", "onErrorResponse ${error.toString()}")
            })
        mRequestQueue.add(mStringRequest3)
    }
    private fun sendRequestCategory() {
        mRequestQueue = Volley.newRequestQueue(this)
        loading2.visibility = View.VISIBLE
        mStringRequest2 = StringRequest(
            Request.Method.GET,
            "https://moviesapi.ir/api/v1/genres",
            { response ->
                val gson = Gson()
                loading2.visibility = View.GONE
                val genreType = object : TypeToken<ArrayList<GenresItem>>(){}.type
                val catList: ArrayList<GenresItem> = gson.fromJson<ArrayList<GenresItem>>(response, genreType)
                adapterCategory = CategoryAdapter(catList)
                recyclerViewCategory.adapter = adapterCategory
            }, {error ->
                loading2.visibility = View.GONE
                Log.i("UiLover", "onErrorResponse ${error.toString()}")
            })
        mRequestQueue.add(mStringRequest2)
    }

    private fun banners() {
        val slidersList = mutableListOf<SlidersItems>()
        slidersList.add(SlidersItems(R.drawable.wide))
        slidersList.add(SlidersItems(R.drawable.wide1))
        slidersList.add(SlidersItems(R.drawable.wide3))

        viewPager2.adapter = SlidersAdapter(slidersList, viewPager2)
        viewPager2.clipToPadding = false
        viewPager2.clipChildren = false
        viewPager2.offscreenPageLimit = 3
        viewPager2.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER

        val compositePageTransform = CompositePageTransformer()
        compositePageTransform.addTransformer(MarginPageTransformer(40))
        compositePageTransform.addTransformer(object : ViewPager2.PageTransformer {
            override fun transformPage(page: View, position: Float) {
                val r = 1 - abs(position)
                page.scaleY = (0.85 + r * 0.15f).toFloat()
            }
        })
        viewPager2.setPageTransformer(compositePageTransform)
        viewPager2.currentItem = 1
        viewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                slideHandler.removeCallbacks(sliderRunnable())
            }
        })
    }

    fun sliderRunnable(): Runnable = Runnable{
        viewPager2.currentItem = viewPager2.currentItem + 1
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