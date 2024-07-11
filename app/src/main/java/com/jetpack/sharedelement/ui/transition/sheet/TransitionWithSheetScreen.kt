@file:OptIn(ExperimentalSharedTransitionApi::class)

package com.jetpack.sharedelement.ui.transition.sheet

import android.content.res.Configuration
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jetpack.sharedelement.R
import com.jetpack.sharedelement.ui.theme.SharedElementTransitionTheme

/**
 * Composable function for the shared element transition demo with sheet component
 */
@Composable
fun TransitionWithSheetScreen(
    modifier: Modifier = Modifier,
    onBack: () -> Unit
) {
    var showDetails by remember { mutableStateOf(false) }

    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.sheet_component_animation)) },
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
                .padding(paddingValues)
                .background(Color.LightGray)
                .fillMaxSize(),
            showDetails = showDetails,
            onShowDetails = { showDetails = true },
            onBack = { showDetails = false }
        )
    }
}

/**
 * Composable function demonstrating a Sheet component animation.
 * Manages the state to show details using SharedTransitionLayout and AnimatedContent.
 */
@Composable
private fun MainContent(
    modifier: Modifier = Modifier,
    showDetails: Boolean,
    onShowDetails: () -> Unit,
    onBack: () -> Unit
) {
    val commonAlbumModifier = Modifier
            .padding(10.dp)
            .clip(MaterialTheme.shapes.small.copy(all = CornerSize(20.dp)))
            .background(Color.White)

    SharedTransitionLayout(
        modifier = modifier
    ) {
        AnimatedContent(
            targetState = showDetails,
            label = "transition"
        ) { targetState ->
            if (!targetState) {
                AlbumScreen(
                    modifier = Modifier
                        .height(100.dp)
                        .then(commonAlbumModifier)
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = null,
                            onClick = onShowDetails
                        ),
                    animatedVisibilityScope = this@AnimatedContent
                )
            } else {
                AlbumDetailScreen(
                    modifier = Modifier
                        .fillMaxWidth()
                        .then(commonAlbumModifier)
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = null,
                            onClick = onBack
                        ),
                    animatedVisibilityScope = this@AnimatedContent
                )
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
private fun TransitionWithSheetScreenPreview() {
    SharedElementTransitionTheme {
        TransitionWithSheetScreen(onBack = { /* Handle Play Action */ })
    }
}

@Composable
@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
private fun MainContentWithShowDetailsPreview() {
    SharedElementTransitionTheme {
        MainContent(
            showDetails = true,
            onShowDetails = { /* Handle Click Action */ },
            onBack = { /* Handle Click Action */ }
        )
    }
}