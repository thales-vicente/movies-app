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
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.bumptech.glide.Glide
import com.example.moviesapp.Adapters.ActorsAdapter
import com.example.moviesapp.Adapters.CategoryAdapter
import com.example.moviesapp.Adapters.CategoryEachFilmAdapter
import com.example.moviesapp.Adapters.FilmAdapter
import com.example.moviesapp.Domain.FilmItem
import com.example.moviesapp.R
import com.example.moviesapp.databinding.ActivityDetailBinding
import com.google.gson.Gson
import kotlin.properties.Delegates

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
    private lateinit var adapterActorList: RecyclerView.Adapter<ActorsAdapter.ViewHolder>
    private lateinit var adapterCategory: RecyclerView.Adapter<CategoryEachFilmAdapter.ViewHolder>
    private var idFilm = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        idFilm = intent.getIntExtra("id", 0)
        titleTxt = binding.tvMovieName
        progressBar = binding.progressBarDetail
        scrollView =  binding.scrollView
        pic = binding.ivFilmDetail
        movieRateTxt =  binding.tvMovieStar
        movieTimeTxt = binding.tvMovieTime
        movieSummaryInfo = binding.tvSummaryDescription
        movieActorsInfo = binding.tvActorInfo
        btnBack = binding.btnBack
        recyclerViewCategory = binding.genreView
        recyclerViewCategory.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        recyclerViewActors = binding.imagesRecycler
        recyclerViewActors.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        sendRequest()

        btnBack.setOnClickListener {
            finish()
        }
    }
    private fun sendRequest(){
        mRequestQueue = Volley.newRequestQueue(this)
        progressBar.visibility = View.VISIBLE
        scrollView.visibility = View.GONE
        mStringRequest = StringRequest(Request.Method.GET,
            "https://moviesapi.ir/api/v1/movies/$idFilm", { response ->
                 val gson = Gson()
                progressBar.visibility = View.GONE
                scrollView.visibility = View.VISIBLE

                val item: FilmItem = gson.fromJson(response, FilmItem::class.java)
                Glide.with(this)
                    .load(item.poster)
                    .into(pic)
                titleTxt.text = item.title
                movieRateTxt.text =  item.imdbRating
                movieTimeTxt.text =  item.runtime
                movieSummaryInfo.text = item.plot
                movieActorsInfo.text = item.actors
                if (item.images!=null){
                    adapterActorList = ActorsAdapter(item.images)
                    recyclerViewActors.adapter = adapterActorList
                }
                if (item.genres!= null){
                    adapterCategory = CategoryEachFilmAdapter(item.genres)
                    recyclerViewCategory.adapter = adapterCategory
                }

        }, {error ->
                progressBar.visibility = View.GONE
        })
        mRequestQueue.add(mStringRequest)
    }
}