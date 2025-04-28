package com.example.moviesapp.Domain

import com.fasterxml.jackson.annotation.JsonProperty

data class FilmItem(
    val id: Long,
    val title: String,
    val poster: String,
    val year: String,
    val rated: String,
    val released: String,
    val runtime: String,
    val director: String,
    val writer: String,
    val actors: String,
    val plot: String,
    val country: String,
    val awards: String,
    val metascore: String,
    @JsonProperty("imdb_rating")
    val imdbRating: String,
    @JsonProperty("imdb_votes")
    val imdbVotes: String,
    @JsonProperty("imdb_id")
    val imdbId: String,
    val type: String,
    val genres: List<String>,
    val images: List<String>,
)
