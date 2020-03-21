package com.nepoapp.parallax.source.RecyclerViews.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.nepoapp.parallax.R
import com.nepoapp.parallax.core.Film
import com.nepoapp.parallax.databinding.FilmViewHolderBinding
import com.nepoapp.parallax.source.RecyclerViews.viewHolder.FilmViewHolder

class FilmAdapter() : RecyclerView.Adapter<FilmViewHolder>(){

    private var films: List<Film> = ArrayList()

    init {
        setHasStableIds(true)
    }

    fun setFilmsList(films: List<Film>) {
        this.films = films
    }

    override fun getItemId(position: Int): Long = films[position].hashCode().toLong()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: FilmViewHolderBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.film_view_holder, parent, false)
        return FilmViewHolder(binding)
    }

    override fun getItemCount(): Int = if (films.isNotEmpty()) films.size else 0
    override fun onBindViewHolder(holder: FilmViewHolder, position: Int) =
        holder.bindView(films[position])
}