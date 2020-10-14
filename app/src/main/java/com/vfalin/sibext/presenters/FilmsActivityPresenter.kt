package com.vfalin.sibext.presenters

import com.vfalin.sibext.models.FilmsResponseUI

interface FilmsActivityPresenter {

    fun loadFilms()

    fun getFilms(): FilmsResponseUI

    fun onDestroy()
}