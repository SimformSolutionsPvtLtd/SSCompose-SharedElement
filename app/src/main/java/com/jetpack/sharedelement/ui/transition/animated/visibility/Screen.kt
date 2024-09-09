package com.jetpack.sharedelement.ui.transition.animated.visibility

sealed class Screen(val route: String) {
    data object DessertsScreen : Screen("list")
    data object DessertDetailsScreen : Screen("details/{desertId}") {
        fun createRoute(dessertId: Int) = "details/$dessertId"
    }
}