package com.vfalin.sibext.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.vfalin.sibext.R
import com.vfalin.sibext.models.FilmsResponseUI
import com.vfalin.sibext.ui.activities.adapters.FilmsAdapter
import com.vfalin.sibext.ui.activities.adapters.GenresAdapter
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.scope.currentScope

class FilmsActivity : AppCompatActivity(), FilmsActivityContract.View {
    override val presenter: FilmsActivityContract.Presenter by currentScope.inject()

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

    private fun initViews() {
        filmsLayoutManager = GridLayoutManager(this, 2)
        filmsAdapter = FilmsAdapter()
        filmsRecycler = findViewById<RecyclerView>(R.id.activity_films_recycler).apply {
            layoutManager = filmsLayoutManager
            adapter = filmsAdapter
        }
        genresLayoutManager = GridLayoutManager(this, 2, GridLayoutManager.HORIZONTAL, false)
        genresAdapter = GenresAdapter { genre -> sortFilms(genre) }
        genresRecycler = findViewById<RecyclerView>(R.id.activity_films_genres_recycler).apply {
            layoutManager = genresLayoutManager
            adapter = genresAdapter
        }
    }

    private fun initListeners() {
        activity_films_load_button.setOnClickListener {
            it.visibility = View.GONE
            startLoadFilms()
        }
    }

    private fun startLoadFilms() {
        presenter.loadFilms()
        activity_films_progress.visibility = View.VISIBLE
    }

    private fun sortFilms(genre: String) {
        filmsAdapter.sort(genre)
    }

    override fun updateFilms(films: FilmsResponseUI) {
        if (films.films != null) {
            filmsAdapter.updateList(films.films)
            genresAdapter.updateList(films.films)
            if (films.films.isEmpty()) {
                activity_films_load_button.visibility = View.VISIBLE
            }
        }
        if (films.error != null) {
            Snackbar.make(
                activity_films_root_layout,
                films.error.localizedMessage,
                Snackbar.LENGTH_LONG
            ).show()
        }
        activity_films_progress.visibility = View.GONE
    }
}