package com.jetpack.sharedelement.model

import androidx.annotation.DrawableRes

data class Dessert(
    val id: Int,
    val name: String,
    @DrawableRes val image: Int
)