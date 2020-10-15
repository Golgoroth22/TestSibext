package com.vfalin.sibext.di.modules

import com.vfalin.sibext.network.service.FilmsService
import com.vfalin.sibext.presenters.FilmsActivityPresenter
import com.vfalin.sibext.repositories.FilmsActivityRepository
import com.vfalin.sibext.ui.activities.FilmsActivity
import com.vfalin.sibext.ui.activities.FilmsActivityContract
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit

class FilmsActivityModule {
    val module = module {
        scope(named<FilmsActivity>()) {
            scoped<FilmsActivityContract.Presenter> {
                FilmsActivityPresenter(
                    FilmsActivityRepository(
                        (get() as Retrofit).create(FilmsService::class.java)
                    )
                )
            }
        }
    }
}