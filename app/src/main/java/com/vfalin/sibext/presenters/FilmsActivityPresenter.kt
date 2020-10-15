package com.vfalin.sibext.presenters

import com.vfalin.sibext.models.FilmsResponseUI
import com.vfalin.sibext.network.pojo.FilmsResponse
import com.vfalin.sibext.ui.activities.FilmsActivityContract
import timber.log.Timber

class FilmsActivityPresenter(
    private var view: FilmsActivityContract.View?,
    private val repository: FilmsActivityContract.Model
) : FilmsActivityContract.Presenter {

    private var filmsResponse = FilmsResponseUI()

    override fun loadFilms() {
        filmsResponse = FilmsResponseUI()
        repository.getFilms(
            { response -> receiveSuccessfulResponse(response) },
            { error -> receiveErrorResponse(error) })
    }

    override fun onDestroy() {
        view = null
    }

    private fun receiveSuccessfulResponse(response: FilmsResponse) {
        filmsResponse = FilmsResponseUI(response.films.map { it.convertTo() })
        view?.updateFilms(filmsResponse)
        Timber.i("FilmsActivityPresenterImpl receiveSuccessfulResponse $response")
    }

    private fun receiveErrorResponse(t: Throwable) {
        filmsResponse = FilmsResponseUI(error = t)
        view?.updateFilms(filmsResponse)
        Timber.e("FilmsActivityPresenterImpl receiveFailureResponse ${t.localizedMessage}")
    }
}