package com.vfalin.sibext.presenters

import com.vfalin.sibext.models.FilmsResponseUI
import com.vfalin.sibext.network.pojo.FilmsResponse
import com.vfalin.sibext.ui.activities.FilmsActivityContract
import timber.log.Timber

class FilmsActivityPresenter(
    private val repository: FilmsActivityContract.Model
) : FilmsActivityContract.Presenter {
    private var mView: FilmsActivityContract.View? = null
    private var filmsResponse = FilmsResponseUI()

    override fun loadFilms() {
        filmsResponse = FilmsResponseUI()
        repository.getFilms(
            { response -> receiveSuccessfulResponse(response) },
            { error -> receiveErrorResponse(error) })
    }

    override fun onAttach(view: FilmsActivityContract.View) {
        mView = view
    }

    override fun onDestroy() {
        mView = null
    }

    private fun receiveSuccessfulResponse(response: FilmsResponse) {
        filmsResponse = FilmsResponseUI(response.films.map { it.convertTo() })
        mView?.updateFilms(filmsResponse)
        Timber.i("FilmsActivityPresenterImpl receiveSuccessfulResponse $response")
    }

    private fun receiveErrorResponse(t: Throwable) {
        filmsResponse = FilmsResponseUI(error = t)
        mView?.updateFilms(filmsResponse)
        Timber.e("FilmsActivityPresenterImpl receiveFailureResponse ${t.localizedMessage}")
    }
}