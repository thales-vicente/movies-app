package com.example.moviesapp.Activities

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.ScrollView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.widget.NestedScrollView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.moviesapp.Adapters.CategoryAdapter
import com.example.moviesapp.Adapters.FilmAdapter
import com.example.moviesapp.R
import com.example.moviesapp.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    private lateinit var mRequestQueue: RequestQueue
    private lateinit var mStringRequest: StringRequest
    private lateinit var titleTxt: TextView
    private lateinit var progressBar: ProgressBar
    private lateinit var scrollView: NestedScrollView
    private lateinit var pic: ImageView
    private lateinit var movieRateTxt: TextView
    private lateinit var movieTimeTxt: TextView
    private lateinit var movieSummaryInfo: TextView
    private lateinit var movieActorsInfo: TextView
    private lateinit var btnBack: ImageView
    private lateinit var recyclerViewCategory: RecyclerView
    private lateinit var recyclerViewActors: RecyclerView
    private lateinit var adapterActorList: RecyclerView.Adapter<.ViewHolder>
    private lateinit var adapterCategory: RecyclerView.Adapter<CategoryAdapter.ViewHolder>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        titleTxt = binding.tvMovieName
        progressBar = binding.progressBarDetail
        scrollView =  binding.scrollView
        pic = binding.ivFilmDetail
        movieRateTxt =  binding.tvMovieStar
        movieTimeTxt = binding.tvMovieTime
        movieSummaryInfo = binding.tvSummary
        movieActorsInfo = binding.tvActorInfo
        btnBack = binding.btnBack
        recyclerViewCategory = binding.genreView
        recyclerViewCategory.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        recyclerViewActors = binding.imagesRecycler
        recyclerViewActors.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        btnBack.setOnClickListener {
            finish()
        }
    }
    private fun sendRequest(){
        mRequestQueue = Volley.newRequestQueue(this)
        progressBar.visibility = View.VISIBLE
        scrollView.visibility = View.GONE
    }
}