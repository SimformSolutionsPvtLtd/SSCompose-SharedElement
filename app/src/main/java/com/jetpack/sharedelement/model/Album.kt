package com.jetpack.sharedelement.model

import androidx.annotation.DrawableRes

data class Album(
    val id: Int,
    val title: String,
    val author: String,
    val year: Int,
    @DrawableRes val cover: Int
)