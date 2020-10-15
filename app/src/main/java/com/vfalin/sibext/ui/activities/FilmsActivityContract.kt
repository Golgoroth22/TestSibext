package com.vfalin.sibext.ui.activities

import com.vfalin.sibext.models.FilmsResponseUI
import com.vfalin.sibext.network.pojo.FilmsResponse

interface FilmsActivityContract {

    interface View {
        fun updateFilms()
    }

    interface Presenter {
        fun loadFilms()

        fun getFilms(): FilmsResponseUI

        fun onDestroy()
    }

    interface Model {
        fun getFilms(onSuccess: (FilmsResponse) -> Unit, onError: (Throwable) -> Unit)
    }
}