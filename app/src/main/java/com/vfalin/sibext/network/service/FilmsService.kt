package com.vfalin.sibext.network.service

import com.vfalin.sibext.network.pojo.FilmsResponse
import io.reactivex.Observable
import retrofit2.http.GET

interface FilmsService {
    @GET("/sequeniatesttask/films.json")
    fun getFilms(): Observable<FilmsResponse>
}