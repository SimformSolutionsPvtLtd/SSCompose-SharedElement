package com.jetpack.sharedelement.navigation

sealed class Screens(val route: String) {
    data object TransitionListScreen : Screens("TransitionListScreen")
    data object TransitionWithNavigationScreen : Screens("TransitionWithNavigationScreen")
    data object TransitionWithoutNavigationScreen : Screens("TransitionWithoutNavigationScreen")
}