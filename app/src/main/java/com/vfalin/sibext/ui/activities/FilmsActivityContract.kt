package com.vfalin.sibext.ui.activities

import com.vfalin.sibext.models.FilmUI
import com.vfalin.sibext.network.pojo.FilmsResponse
import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle
import moxy.viewstate.strategy.alias.Skip

interface FilmsActivityContract {

    interface View : MvpView {
        @AddToEndSingle
        fun showAllFilms(films: List<FilmUI>)

        @AddToEndSingle
        fun showSortedFilms(films: List<FilmUI>, genre: String)

        @Skip
        fun showMessage(message: String)
    }

    interface Presenter {
        fun loadFilms()

        fun sortFilms(genre: String)

        fun onAttach(view: View)

        fun onDestroy()
    }

    interface Model {
        fun getFilms(onSuccess: (FilmsResponse) -> Unit, onError: (Throwable) -> Unit)
    }
}