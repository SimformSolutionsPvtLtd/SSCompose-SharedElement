@file:OptIn(ExperimentalSharedTransitionApi::class)

package com.jetpack.sharedelement.ui.transition.animated.visibility

import android.content.res.Configuration
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.jetpack.sharedelement.R
import com.jetpack.sharedelement.data.FakeDataProvider
import com.jetpack.sharedelement.model.Dessert
import com.jetpack.sharedelement.ui.theme.SharedElementTransitionTheme

/**
 * Composable function for shared element transition demo with AnimatedVisibility.
 */
@Composable
fun TransitionWithAnimatedVisibilityScreen(
    modifier: Modifier = Modifier,
    onBack: () -> Unit
) {
    val desserts = remember(Unit) { FakeDataProvider.getDesserts() }
    val navController = rememberNavController()

    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                title = {
                    Text(stringResource(R.string.animated_visibility_shared_element))
                },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = null)
                    }
                }
            )
        }
    ) { paddingValues ->
        MainContent(
            modifier = Modifier
                .padding(paddingValues),
            navController = navController,
            desserts = desserts
        )
    }
}

/**
 * Composable function for displaying a list of desserts with shared element transitions.
 * Applies animations and blur effect when a dessert is selected.
 */
@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
private fun MainContent(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    desserts: List<Dessert>
) {
    SharedTransitionLayout(
        modifier = modifier
    ) {
        NavHost(
            navController = navController,
            startDestination = Screen.DessertsScreen.route
        ) {
            composable(route = Screen.DessertsScreen.route) {
                DessertsScreen(
                    modifier = Modifier
                        .background(Color.LightGray.copy(alpha = 0.5f))
                        .padding(16.dp),
                    desserts = desserts,
                    animatedVisibilityScope = this,
                    sharedTransitionScope = this@SharedTransitionLayout,
                    onDessertClicked = { dessertId ->
                        navController.navigate(
                            Screen.DessertDetailsScreen.createRoute(dessertId)
                        )
                    }
                )
            }

            composable(
                route = Screen.DessertDetailsScreen.route,
                arguments = listOf(navArgument("desertId") { type = NavType.IntType })
            ) {
                val dessertId = it.arguments?.getInt("desertId") ?: -1
                val dessert = desserts[dessertId]

                DessertDetailScreen(
                    modifier = Modifier
                        .fillMaxSize(),
                    animatedVisibilityScope = this,
                    dessert = dessert,
                    onSaveClick = {
                        navController.navigateUp()
                    }
                )
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
private fun MainContentPreview() {
    SharedElementTransitionTheme {
        MainContent(
            desserts = FakeDataProvider.getDesserts(),
            navController = rememberNavController()
        )
    }
}

@Composable
@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
private fun TransitionWithAnimatedVisibilityScreenPreview() {
    SharedElementTransitionTheme {
        TransitionWithAnimatedVisibilityScreen(
            onBack = { /* Handle Click Action */ }
        )
    }
}