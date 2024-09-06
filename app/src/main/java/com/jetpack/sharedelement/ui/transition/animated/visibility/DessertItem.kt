@file:OptIn(ExperimentalSharedTransitionApi::class)

package com.jetpack.sharedelement.ui.transition.animated.visibility

import android.content.res.Configuration
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jetpack.sharedelement.data.FakeDataProvider
import com.jetpack.sharedelement.model.Dessert
import com.jetpack.sharedelement.ui.theme.SharedElementTransitionTheme
import com.jetpack.sharedelement.ui.transition.animated.visibility.components.DesertContents

/**
 * Composable function for displaying a dessert item with shared element transition.
 * Includes animations for visibility changes.
 */
@Composable
fun SharedTransitionScope.DessertItem(
    modifier: Modifier = Modifier,
    dessert: Dessert,
    visible: Boolean,
    onClick: () -> Unit
) {
    AnimatedVisibility(
        modifier = modifier,
        visible = visible,
        enter = fadeIn(animationSpec = sharedElementTransitionSpec()) + scaleIn(
            sharedElementTransitionSpec()
        ),
        exit = fadeOut(animationSpec = sharedElementTransitionSpec()) + scaleOut(
            sharedElementTransitionSpec()
        )
    ) {
        Box(
            modifier = Modifier
                .sharedBounds(
                    sharedContentState = rememberSharedContentState(key = "${dessert.name}-bounds"),
                    animatedVisibilityScope = this@AnimatedVisibility,
                    boundsTransform = dessertBoundsTransition,
                    clipInOverlayDuringTransition = OverlayClip(
                        clipShape = MaterialTheme.shapes.small.copy(CornerSize(15.dp))
                    )
                )
                .background(
                    color = MaterialTheme.colors.onPrimary,
                    shape = MaterialTheme.shapes.small.copy(all = CornerSize(15.dp))
                )
                .clip(shape = MaterialTheme.shapes.small.copy(all = CornerSize(15.dp)))
        ) {
            DesertContents(
                modifier = Modifier
                    .sharedElement(
                        state = rememberSharedContentState(key = dessert.name),
                        animatedVisibilityScope = this@AnimatedVisibility,
                        boundsTransform = dessertBoundsTransition
                    )
                    .clickable(onClick = onClick),
                name = dessert.name,
                image = dessert.image
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
private fun DessertItemPreview() {
    val dessert = FakeDataProvider.getDesserts()[0]

    SharedElementTransitionTheme {
        SharedTransitionLayout {
            DessertItem(
                dessert = dessert,
                visible = true,
                onClick = { /* Handle Click Action*/ }
            )
        }
    }
}