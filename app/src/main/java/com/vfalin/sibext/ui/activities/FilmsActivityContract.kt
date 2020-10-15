package com.vfalin.sibext.ui.activities

import com.vfalin.sibext.models.FilmsResponseUI
import com.vfalin.sibext.network.pojo.FilmsResponse

interface FilmsActivityContract {

    interface View {
        val presenter: Presenter

        fun updateFilms(films: FilmsResponseUI)
    }

    interface Presenter {
        fun loadFilms()

        fun onAttach(view: View)

        fun onDestroy()
    }

    interface Model {
        fun getFilms(onSuccess: (FilmsResponse) -> Unit, onError: (Throwable) -> Unit)
    }
}