package com.vfalin.sibext.ui.activities.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.vfalin.sibext.R
import com.vfalin.sibext.models.FilmUI
import com.vfalin.sibext.models.GenreUI

class GenresAdapter(
    private val genreSelectorListener: (String) -> Unit
) : RecyclerView.Adapter<GenresAdapter.GenreViewHolder>() {
    private var adapterList = emptyList<GenreUI>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenreViewHolder {
        return GenreViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_genre_layout, parent, false)
        )
    }

    override fun onBindViewHolder(holder: GenreViewHolder, position: Int) {
        holder.bind(adapterList[position])
    }

    override fun getItemCount() = adapterList.size

    fun updateList(list: List<FilmUI>) {
        val genres = mutableSetOf<String>()
        list.forEach {
            it.genres.forEach { genre -> genres.add(genre) }
        }
        adapterList = genres.map { GenreUI(it, false) }.toList()
        notifyDataSetChanged()
    }

    fun selectItem(genre: GenreUI) {
        adapterList.map {
            it.isSelected = it.genre == genre.genre
        }
        notifyDataSetChanged()
    }

    inner class GenreViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val button: Button = itemView.findViewById(R.id.item_genre_button)

        fun bind(genre: GenreUI) {
            button.text = genre.genre
            if (genre.isSelected) {
                button.backgroundTintList =
                    ContextCompat.getColorStateList(button.context, R.color.colorPrimaryDark)
            } else {
                button.backgroundTintList =
                    ContextCompat.getColorStateList(button.context, R.color.colorPrimary)
            }
            button.setOnClickListener {
                selectItem(genre)
                genreSelectorListener.invoke(genre.genre)
            }
        }
    }
}