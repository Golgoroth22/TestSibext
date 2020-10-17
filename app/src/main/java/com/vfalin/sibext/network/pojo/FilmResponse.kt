package com.vfalin.sibext.network.pojo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.vfalin.sibext.models.FilmUI

data class FilmResponse(
    @SerializedName("id")
    @Expose
    val id: Int,
    @SerializedName("localized_name")
    @Expose
    val localizedName: String,
    @SerializedName("name")
    @Expose
    val name: String,
    @SerializedName("year")
    @Expose
    val year: Int,
    @SerializedName("rating")
    @Expose
    val rating: Double,
    @SerializedName("image_url")
    @Expose
    val imageUrl: String? = null,
    @SerializedName("description")
    @Expose
    val description: String,
    @SerializedName("genres")
    @Expose
    val genres: List<String>
) {
    fun convertTo() = FilmUI(id, localizedName, imageUrl, genres)
}