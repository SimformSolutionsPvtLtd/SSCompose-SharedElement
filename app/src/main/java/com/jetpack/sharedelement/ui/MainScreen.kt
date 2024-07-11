package com.jetpack.sharedelement.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.jetpack.sharedelement.navigation.Screens
import com.jetpack.sharedelement.ui.transition.TransitionsScreen
import com.jetpack.sharedelement.ui.transition.animated.visibility.TransitionWithAnimatedVisibilityScreen
import com.jetpack.sharedelement.ui.transition.fab.TransitionWithFabComponentScreen
import com.jetpack.sharedelement.ui.transition.sheet.TransitionWithSheetScreen
import com.jetpack.sharedelement.ui.transition.text.transform.TransitionWithTextTransformScreen
import com.jetpack.sharedelement.ui.transition.with.navigation.TransitionWithNavigationScreen
import com.jetpack.sharedelement.ui.transition.without.navigation.TransitionWithoutNavigationScreen

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = Screens.TransitionListScreen.route
    ) {
        composable(Screens.TransitionListScreen.route) {
           TransitionsScreen(
               modifier = Modifier.fillMaxSize(),
               navigateTo = { screen ->
                   navController.navigate(screen)
               }
            )
        }
        composable(Screens.TransitionWithNavigationScreen.route) {
            TransitionWithNavigationScreen(
                modifier = Modifier.fillMaxSize(),
                onBack = {
                    navController.popBackStack()
                }
            )
        }
        composable(Screens.TransitionWithoutNavigationScreen.route) {
            TransitionWithoutNavigationScreen(
                modifier = Modifier.fillMaxSize(),
                onBack = {
                    navController.popBackStack()
                }
            )
        }
        composable(Screens.TransitionWithTextTransformScreen.route) {
            TransitionWithTextTransformScreen(
                onBack = {
                    navController.popBackStack()
                }
            )
        }
        composable(Screens.TransitionWithAnimatedVisibilityScreen.route) {
            TransitionWithAnimatedVisibilityScreen(
                onBack = {
                    navController.popBackStack()
                }
            )
        }
        composable(Screens.TransitionWithSheetScreen.route) {
            TransitionWithSheetScreen(
                onBack = {
                    navController.popBackStack()
                }
            )
        }
        composable(Screens.TransitionWithFabComponentScreen.route) {
            TransitionWithFabComponentScreen(
                onBack = {
                    navController.popBackStack()
                }
            )
        }
    }
}