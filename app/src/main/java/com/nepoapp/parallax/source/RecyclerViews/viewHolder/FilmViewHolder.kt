package com.nepoapp.parallax.source.RecyclerViews.viewHolder

import androidx.interpolator.view.animation.FastOutLinearInInterpolator
import androidx.recyclerview.widget.RecyclerView
import com.nepoapp.parallax.core.Film
import com.nepoapp.parallax.databinding.FilmViewHolderBinding
import kotlin.math.abs

class FilmViewHolder(view: FilmViewHolderBinding) : RecyclerView.ViewHolder(view.root){

    private val binding : FilmViewHolderBinding = view
    private val interpolator = FastOutLinearInInterpolator()

    var offset : Float = 0f
       set(valueOffset) {
           field = valueOffset.coerceIn(-1f,1f)

           /**
            * A value less than 0 means that the views will move to the left,
            * and a value greater will move to the right.
            **/
           val direction = if (field < 0) -1f else 1f
           /**
            * The reason for sending abs(field) to the interpolator
            * is that it expects a value between 0 and 1, and will cap any negative value to 0.
            **/
           val interpolatedValue = interpolator.getInterpolation(abs(field))

           val translationX = direction * interpolatedValue * itemView.measuredWidth

           binding.apply {
               title.translationX = translationX
               description.translationX = translationX
               thumbnail.translationX = translationX
           }

       }

    fun bindView(film : Film){
        binding.title.setText(film.title)
        binding.description.setText(film.description)
        binding.post.setImageResource(film.image)
        binding.thumbnail.setImageResource(film.image)
    }

}