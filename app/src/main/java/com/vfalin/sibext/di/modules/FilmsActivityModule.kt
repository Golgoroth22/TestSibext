package com.vfalin.sibext.di.modules

import com.vfalin.sibext.network.service.FilmsService
import com.vfalin.sibext.presenters.FilmsActivityPresenter
import com.vfalin.sibext.repositories.FilmsActivityRepository
import com.vfalin.sibext.ui.activities.FilmsActivity
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit

val filmsActivityModule = module {
    scope(named<FilmsActivity>()) {
        scoped {
            FilmsActivityPresenter(
                FilmsActivityRepository(
                    (get() as Retrofit).create(FilmsService::class.java)
                )
            )
        }
    }
}