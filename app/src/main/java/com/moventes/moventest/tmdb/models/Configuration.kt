package com.moventes.moventest.tmdb.models

import com.google.gson.annotations.SerializedName

data class Configuration(
    @SerializedName("secure_base_url") var imageBaseUrl: String,
    @SerializedName("poster_sizes") var posterSizes: List<String>,
    var genres: Map<Int, String>
)