package com.vfalin.sibext.network.pojo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class FilmsResponse(
    @SerializedName("films")
    @Expose
    val films: List<FilmResponse>
)