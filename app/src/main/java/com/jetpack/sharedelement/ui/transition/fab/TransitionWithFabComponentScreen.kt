@file:OptIn(ExperimentalSharedTransitionApi::class)

package com.jetpack.sharedelement.ui.transition.fab

import android.content.res.Configuration
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.jetpack.sharedelement.R
import com.jetpack.sharedelement.data.FakeDataProvider
import com.jetpack.sharedelement.model.Profile
import com.jetpack.sharedelement.ui.theme.SharedElementTransitionTheme

/**
 * Composable function for the shared element transition demo with fab component
 */
@Composable
fun TransitionWithFabComponentScreen(
    modifier: Modifier = Modifier,
    onBack: () -> Unit
) {
    var showDetails by remember { mutableStateOf(false) }
    val profiles = remember(Unit) { FakeDataProvider.getFabProfiles() }

    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.fab_component_animation)) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = null
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        MainContent(
            modifier = Modifier.padding(paddingValues),
            profiles = profiles,
            showDetails = showDetails,
            onShowDetails = {
                showDetails = true
            },
            onBack = {
                showDetails = false
            }
        )
    }
}

/**
 * Composable function demonstrating a FAB component animation.
 * Manages the state to show details using SharedTransitionLayout and AnimatedContent.
 */
@Composable
private fun MainContent(
    modifier: Modifier = Modifier,
    profiles: List<Profile>,
    showDetails: Boolean,
    onShowDetails: () -> Unit,
    onBack: () -> Unit
) {
    SharedTransitionLayout(
        modifier = modifier
    ) {
        AnimatedContent(
            targetState = showDetails,
            label = "basic_transition"
        ) { targetState ->
            if (!targetState) {
                FabMainContent(
                    modifier = Modifier
                        .fillMaxSize()
                        .sharedBounds(
                            sharedContentState = rememberSharedContentState(key = "bounds"),
                            animatedVisibilityScope = this@AnimatedContent,
                            enter = fabEnterAnimation,
                            exit = fabExitAnimation,
                            boundsTransform = fabBoundsTransform
                        )
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = null,
                            onClick = onShowDetails
                        ),
                    animatedVisibilityScope = this@AnimatedContent
                )
            } else {
                FabDetailsContent(
                    modifier = Modifier.fillMaxSize(),
                    animatedVisibilityScope = this@AnimatedContent,
                    profiles = profiles,
                    onBack = onBack
                )
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
private fun TransitionWithFabComponentScreenPreview() {
    SharedElementTransitionTheme {
        TransitionWithFabComponentScreen(
            modifier = Modifier.fillMaxSize(),
            onBack = { /* Handle Click Action */ }
        )
    }
}

@Composable
@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
private fun MainContentWithShowDetailsPreview() {
    SharedElementTransitionTheme {
        MainContent(
            profiles = FakeDataProvider.getFabProfiles(),
            showDetails = true,
            onShowDetails = { /* Handle Click Action */ },
            onBack = { /* Handle Click Action */ }
        )
    }
}