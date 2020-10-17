package com.vfalin.sibext.models

data class FilmUI(
    val id: Int,
    val localizedName: String,
    val imageUrl: String?,
    val genres: List<String>
)