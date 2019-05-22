package com.moventes.moventest.tmdb.models

import com.google.gson.annotations.SerializedName
import java.util.*

data class Movie(
    var id: Int,
    var title: String,
    var overview: String,
    @SerializedName("poster_path") var posterPath: String,
    @SerializedName("release_date") var release: Date,
    var runtime: Int,
    @SerializedName("vote_average") var voteAverage: Double,
    @SerializedName("vote_count") var voteCount: Int,
    var adult: Boolean,
    @SerializedName("genre_ids") var genreIds: List<Int>
)