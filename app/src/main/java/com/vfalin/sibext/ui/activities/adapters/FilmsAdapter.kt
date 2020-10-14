package com.vfalin.sibext.ui.activities.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.vfalin.sibext.R
import com.vfalin.sibext.models.FilmUI

class FilmsAdapter : RecyclerView.Adapter<FilmsAdapter.FilmViewHolder>() {
    private var adapterList = emptyList<FilmUI>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmViewHolder {
        return FilmViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_film_layout, parent, false)
        )
    }

    override fun onBindViewHolder(holder: FilmViewHolder, position: Int) {
        holder.bind(adapterList[position])
    }

    override fun getItemCount() = adapterList.size

    fun updateList(films: List<FilmUI>) {
        adapterList = films
        notifyDataSetChanged()
    }

    class FilmViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val title: TextView = itemView.findViewById(R.id.item_film_title_text)
        private val image: ImageView = itemView.findViewById(R.id.item_film_image)

        fun bind(film: FilmUI) {
            title.text = film.localizedName
            Glide.with(image.context)
                .load(film.imageUrl)
                .thumbnail(0.3f)
                .error(R.drawable.film_no_image)
                .into(image)
        }
    }
}