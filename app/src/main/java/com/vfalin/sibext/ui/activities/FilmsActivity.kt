package com.vfalin.sibext.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.vfalin.sibext.R
import com.vfalin.sibext.network.LoggingInterceptor
import com.vfalin.sibext.network.service.FilmsService
import com.vfalin.sibext.presenters.FilmsActivityPresenter
import com.vfalin.sibext.repositories.FilmsActivityRepository
import com.vfalin.sibext.ui.activities.adapters.FilmsAdapter
import com.vfalin.sibext.utils.Constants
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class FilmsActivity : AppCompatActivity(), FilmsActivityContract.View {
    private lateinit var presenter: FilmsActivityPresenter

    private lateinit var filmsRecycler: RecyclerView
    private lateinit var filmsAdapter: FilmsAdapter
    private lateinit var filmsLayoutManager: GridLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initPresenter()
        initViews()
    }

    private fun initViews() {
        filmsLayoutManager = GridLayoutManager(this, 2)
        filmsAdapter = FilmsAdapter()
        filmsRecycler = findViewById<RecyclerView>(R.id.activity_films_recycler).apply {
            layoutManager = filmsLayoutManager
            adapter = filmsAdapter
        }
        startLoadFilms()
    }

    private fun initPresenter() {
        val retrofit = Retrofit.Builder()
            .baseUrl(Constants.FILMS_BASE_URL)
            .client(
                OkHttpClient()
                    .newBuilder()
                    .addNetworkInterceptor(LoggingInterceptor())
                    .build()
            )
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        presenter = FilmsActivityPresenter(
            this,
            FilmsActivityRepository(retrofit.create(FilmsService::class.java))
        )
    }

    private fun startLoadFilms() {
        presenter.loadFilms()
        activity_films_progress.visibility = View.VISIBLE
    }

    override fun updateFilms() {
        val films = presenter.getFilms()
        if (films.films != null) {
            filmsAdapter.updateList(films.films)
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