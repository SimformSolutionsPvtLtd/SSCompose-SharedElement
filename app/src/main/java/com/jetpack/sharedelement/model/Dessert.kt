package com.jetpack.sharedelement.model

import androidx.annotation.DrawableRes

data class Dessert(
    val name: String,
    @DrawableRes val image: Int
)