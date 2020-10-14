package com.vfalin.sibext.repositories

import com.vfalin.sibext.network.pojo.FilmsResponse

interface FilmsActivityRepository {

    fun getFilms(onSuccess: (FilmsResponse) -> Unit, onError: (Throwable) -> Unit)
}