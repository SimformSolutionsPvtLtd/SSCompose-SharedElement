package com.jetpack.sharedelement.navigation

sealed class Screens(val route: String) {
    data object TransitionListScreen : Screens("TransitionListScreen")
    data object TransitionWithNavigationScreen : Screens("TransitionWithNavigationScreen")
    data object TransitionWithoutNavigationScreen : Screens("TransitionWithoutNavigationScreen")
    data object TransitionWithTextTransformScreen : Screens("TransitionWithTextTransformScreen")
    data object TransitionWithAnimatedVisibilityScreen : Screens("TransitionWithAnimatedVisibilityScreen")
    data object TransitionWithSheetScreen: Screens("TransitionWithSheetScreen")
    data object TransitionWithFabComponentScreen: Screens("TransitionWithFabComponentScreen")
}