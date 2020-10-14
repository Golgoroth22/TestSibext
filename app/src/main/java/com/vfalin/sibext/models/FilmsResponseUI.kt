package com.vfalin.sibext.models

data class FilmsResponseUI(
    val films: List<FilmUI>? = null,
    val error: Throwable? = null
)