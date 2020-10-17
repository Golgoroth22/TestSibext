package com.vfalin.sibext.ui.activities

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.vfalin.sibext.R
import com.vfalin.sibext.models.FilmUI
import com.vfalin.sibext.presenters.FilmsActivityPresenter
import com.vfalin.sibext.ui.activities.adapters.FilmsAdapter
import com.vfalin.sibext.ui.activities.adapters.GenresAdapter
import kotlinx.android.synthetic.main.activity_main.*
import moxy.MvpAppCompatActivity
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import org.koin.android.scope.currentScope

class FilmsActivity : MvpAppCompatActivity(), FilmsActivityContract.View {
    @InjectPresenter
    lateinit var presenter: FilmsActivityPresenter

    @ProvidePresenter
    fun provide(): FilmsActivityPresenter {
        val activityPresenter: FilmsActivityPresenter by currentScope.inject()
        return activityPresenter
    }

    private lateinit var filmsRecycler: RecyclerView
    private lateinit var filmsAdapter: FilmsAdapter
    private lateinit var filmsLayoutManager: GridLayoutManager

    private lateinit var genresRecycler: RecyclerView
    private lateinit var genresAdapter: GenresAdapter
    private lateinit var genresLayoutManager: GridLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        presenter.onAttach(this)
        initViews()
        initListeners()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }

    override fun showMessage(message: String) {
        Snackbar.make(activity_films_root_layout, message, Snackbar.LENGTH_LONG).show()
        activity_films_progress.visibility = View.GONE
    }

    override fun showAllFilms(films: List<FilmUI>) {
        filmsAdapter.updateList(films)
        genresAdapter.updateList(films)
        if (films.isEmpty()) {
            activity_films_load_button.visibility = View.VISIBLE
        } else {
            activity_films_load_button.visibility = View.GONE
        }
        activity_films_progress.visibility = View.GONE
    }

    override fun showSortedFilms(films: List<FilmUI>, genre: String) {
        filmsAdapter.updateList(films)
        genresAdapter.selectGenre(genre)
    }

    private fun initViews() {
        filmsLayoutManager = GridLayoutManager(this, 2)
        filmsAdapter = FilmsAdapter()
        filmsRecycler = findViewById<RecyclerView>(R.id.activity_films_recycler).apply {
            layoutManager = filmsLayoutManager
            adapter = filmsAdapter
        }
        genresLayoutManager = GridLayoutManager(this, 2, GridLayoutManager.HORIZONTAL, false)
        genresAdapter = GenresAdapter { genre -> presenter.sortFilms(genre) }
        genresRecycler = findViewById<RecyclerView>(R.id.activity_films_genres_recycler).apply {
            layoutManager = genresLayoutManager
            adapter = genresAdapter
        }
    }

    private fun initListeners() {
        activity_films_load_button.setOnClickListener {
            startLoadFilms()
        }
    }

    private fun startLoadFilms() {
        presenter.loadFilms()
        activity_films_progress.visibility = View.VISIBLE
    }
}