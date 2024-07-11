package com.jetpack.sharedelement.model

import androidx.annotation.DrawableRes

data class Coffee(
    val id: Int,
    val name: String,
    val description: String,
    @DrawableRes val image: Int
)