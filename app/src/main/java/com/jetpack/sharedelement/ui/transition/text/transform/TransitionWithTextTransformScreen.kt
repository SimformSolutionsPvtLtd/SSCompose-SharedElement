@file:OptIn(ExperimentalSharedTransitionApi::class)

package com.jetpack.sharedelement.ui.transition.text.transform

import android.content.res.Configuration
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jetpack.sharedelement.R
import com.jetpack.sharedelement.ui.theme.SharedElementTransitionTheme

/**
 * Composable function for the shared element transition demo text component
 */
@Composable
fun TransitionWithTextTransformScreen(
    modifier: Modifier = Modifier,
    onBack: () -> Unit
) {
    var showDetails by remember { mutableStateOf(false) }

    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                title = {
                    Text(stringResource(R.string.text_transform_animation))
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
            modifier = Modifier.padding(paddingValues),
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
 * Composable function demonstrating text transformation animation.
 * Manages the state to show details using SharedTransitionLayout and AnimatedContent.
 */
@Composable
private fun MainContent(
    modifier: Modifier = Modifier,
    showDetails: Boolean,
    onShowDetails: () -> Unit,
    onBack: () -> Unit
) {
    val roundedBoxModifier = Modifier
        .padding(15.dp)
        .border(
            width = 1.dp,
            color = Color.Gray,
            shape = MaterialTheme.shapes.small.copy(all = CornerSize(8.dp))
        )
        .background(
            color = Color.LightGray,
            shape = MaterialTheme.shapes.small.copy(all = CornerSize(8.dp))
        )

    SharedTransitionLayout(
        modifier = modifier
    ) {
        AnimatedContent(
            targetState = showDetails,
            label = "basic_transition"
        ) { targetState ->
            val sharedBoundsModifier = Modifier
                .sharedBounds(
                    sharedContentState = rememberSharedContentState(key = "bounds"),
                    animatedVisibilityScope = this@AnimatedContent,
                    enter = textEnterAnimation,
                    exit = textExitAnimation,
                    boundsTransform = textBoundsTransform
                )

            if (!targetState) {
                Car(
                    modifier = Modifier
                        .fillMaxWidth()
                        .then(roundedBoxModifier)
                        .then(sharedBoundsModifier)
                        .clickable(onClick = onShowDetails),
                    animatedVisibilityScope = this@AnimatedContent
                )
            } else {
                CarDetails(
                    modifier = Modifier
                        .then(roundedBoxModifier)
                        .then(sharedBoundsModifier)
                        .clickable(onClick = onBack),
                    animatedVisibilityScope = this@AnimatedContent,
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
            showDetails = false,
            onShowDetails = { /* Handle Click Action*/ },
            onBack = {/* Handle Click Action*/ }
        )
    }
}

@Composable
@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
private fun MainContentWithShowDetails() {
    SharedElementTransitionTheme {
        MainContent(
            showDetails = true,
            onShowDetails = { /* Handle Click Action*/ },
            onBack = { /* Handle Click Action*/ }
        )
    }
}