package com.example.moviesapp.Domain

import com.fasterxml.jackson.annotation.JsonProperty

data class Root(
    val data: List<Daum>,
    val metadata: Metadata,
)

data class Daum(
    val id: Long,
    val title: String,
    val poster: String,
    val year: String,
    val country: String,
    @JsonProperty("imdb_rating")
    val imdbRating: String,
    val genres: List<String>,
    val images: List<String>,
)

data class Metadata(
    @JsonProperty("current_page")
    val currentPage: String,
    @JsonProperty("per_page")
    val perPage: Long,
    @JsonProperty("page_count")
    val pageCount: Long,
    @JsonProperty("total_count")
    val totalCount: Long,
)

