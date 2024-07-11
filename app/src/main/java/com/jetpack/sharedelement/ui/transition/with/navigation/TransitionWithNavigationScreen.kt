@file:OptIn(ExperimentalSharedTransitionApi::class)

package com.jetpack.sharedelement.ui.transition.with.navigation

import android.content.res.Configuration
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Rect
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
import com.jetpack.sharedelement.model.Album
import com.jetpack.sharedelement.ui.theme.SharedElementTransitionTheme

val boundsTransform = { _: Rect, _: Rect ->
    tween<Rect>(500)
}

/**
 * Composable function for the shared element transition demo with navigation
 */
@Composable
fun TransitionWithNavigationScreen(
    modifier: Modifier = Modifier,
    onBack: () -> Unit
) {
    val navController = rememberNavController()
    val albums = remember(Unit) { FakeDataProvider.getAlbums() }

    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                title = {
                    Text(stringResource(R.string.shared_element_transition_with_navigation))
                },
                navigationIcon = {
                    IconButton(
                        onClick = onBack,
                        content = {
                            Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = null)
                        }
                    )
                }
            )
        }
    ) { paddingValues ->
        MainContent(
            modifier = Modifier.padding(paddingValues),
            navController = navController,
            albums = albums
        )
    }
}

/**
 * Composable function that sets up the navigation and shared element transitions
 * for the album preview and detail screens.
 */
@Composable
private fun MainContent(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    albums: List<Album>
) {
    SharedTransitionLayout(
        modifier = modifier
    ) {
        NavHost(
            navController = navController,
            startDestination = Screen.AlbumsScreen.route
        ) {
            composable(route = Screen.AlbumsScreen.route) {
                AlbumsScreen(
                    albums = albums,
                    animatedVisibilityScope = this,
                    onAlbumClick = { album ->
                        navController.navigate(Screen.AlbumDetailsScreen.createRoute(album.id)) {
                            popUpTo(Screen.AlbumsScreen.route) {
                                inclusive = true
                            }
                        }
                    }
                )
            }

            composable(
                route = Screen.AlbumDetailsScreen.route,
                arguments = listOf(navArgument("albumId") { type = NavType.IntType })
            ) { navBackStackEntry ->
                val albumId = navBackStackEntry.arguments?.getInt("albumId") ?: -1
                val album = albums[albumId]

                AlbumDetailScreen(
                    modifier = Modifier
                        .padding(10.dp)
                        .clip(RoundedCornerShape(30.dp))
                        .background(Color.LightGray.copy(alpha = 0.5f))
                        // Adding shared element for the detailed view of the album cover
                        // The sharedElement modifier is used to specify the shared element for the transition.
                        // - `rememberSharedContentState(key = album.id)` creates a state holder for the shared element with a unique key.
                        // - `animatedVisibilityScope` provides the scope for managing visibility changes during the transition.
                        // - `boundsTransform` defines the transformation applied to the bounds of the shared element during the transition.
                        .sharedElement(
                            state = rememberSharedContentState(key = album.id),
                            animatedVisibilityScope = this,
                            boundsTransform = boundsTransform
                        ),
                    album = album,
                    onBackClick = {
                        navController.navigate(Screen.AlbumsScreen.route) {
                            popUpTo(Screen.AlbumDetailsScreen.createRoute(albumId)) {
                                inclusive = true
                            }
                        }
                    }
                )
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
private fun TransitionWithNavigationScreenPreview() {
    SharedElementTransitionTheme {
        TransitionWithNavigationScreen(onBack = { /* Click Action */ })
    }
}