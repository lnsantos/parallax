package com.nepoapp.parallax.core

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Film (
    @StringRes val title: Int,
    @StringRes val description: Int,
    @DrawableRes val image: Int
)

