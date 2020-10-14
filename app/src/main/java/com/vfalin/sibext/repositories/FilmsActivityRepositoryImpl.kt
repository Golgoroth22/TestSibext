package com.vfalin.sibext.repositories

import com.vfalin.sibext.network.pojo.FilmsResponse
import com.vfalin.sibext.network.service.FilmsService
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

class FilmsActivityRepositoryImpl(private val filmsService: FilmsService) :
    FilmsActivityRepository {

    override fun getFilms(onSuccess: (FilmsResponse) -> Unit, onError: (Throwable) -> Unit) {
        filmsService.getFilms()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<FilmsResponse> {
                override fun onSubscribe(d: Disposable) {
                    Timber.i("FilmsActivityRepositoryImpl getFilms Subscription")
                }

                override fun onNext(response: FilmsResponse) {
                    onSuccess.invoke(response)
                    Timber.i("FilmsActivityRepositoryImpl getFilms onNext")
                }

                override fun onError(throwable: Throwable) {
                    onError.invoke(throwable)
                    Timber.i("FilmsActivityRepositoryImpl getFilms onError")
                }

                override fun onComplete() {
                    Timber.i("FilmsActivityRepositoryImpl getFilms onComplete")
                }
            })
    }
}