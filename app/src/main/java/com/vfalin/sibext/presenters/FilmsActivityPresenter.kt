package com.vfalin.sibext.presenters

import com.vfalin.sibext.models.FilmUI
import com.vfalin.sibext.network.pojo.FilmsResponse
import com.vfalin.sibext.ui.activities.FilmsActivityContract
import moxy.InjectViewState
import moxy.MvpPresenter
import timber.log.Timber

@InjectViewState
class FilmsActivityPresenter(
    private val repository: FilmsActivityContract.Model
) : MvpPresenter<FilmsActivityContract.View>(), FilmsActivityContract.Presenter {
    private var mView: FilmsActivityContract.View? = null
    private var films = emptyList<FilmUI>()

    override fun loadFilms() {
        repository.getFilms(
            { response -> receiveSuccessfulResponse(response) },
            { error -> receiveErrorResponse(error) })
    }

    override fun sortFilms(genre: String) {
        viewState.showSortedFilms(films.filter { it.genres.contains(genre) }, genre)
    }

    override fun onFirstViewAttach() {
        viewState.showAllFilms(films)
    }

    override fun onAttach(view: FilmsActivityContract.View) {
        mView = view
    }

    override fun onDestroy() {
        mView = null
    }

    private fun receiveSuccessfulResponse(response: FilmsResponse) {
        films = response.films.map { it.convertTo() }
        viewState.showAllFilms(films)
        Timber.i("FilmsActivityPresenterImpl receiveSuccessfulResponse $response")
    }

    private fun receiveErrorResponse(t: Throwable) {
        viewState.showMessage(t.localizedMessage)
        Timber.e("FilmsActivityPresenterImpl receiveFailureResponse ${t.localizedMessage}")
    }
}