@file:OptIn(ExperimentalSharedTransitionApi::class)

package com.jetpack.sharedelement.ui.transition.animated.visibility

import android.content.res.Configuration
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
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
 * Composable function for displaying the list of desserts.
 */
@Composable
fun DessertsScreen(
    modifier: Modifier = Modifier,
    desserts: List<Dessert>,
    animatedVisibilityScope: AnimatedVisibilityScope,
    sharedTransitionScope: SharedTransitionScope,
    onDessertClicked: (Int) -> Unit
) {
    with(sharedTransitionScope) {
        LazyColumn(
            modifier = modifier,
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            itemsIndexed(desserts) { _, dessert ->
                Box(
                    modifier = Modifier
                        .animateContentSize()
                        .sharedBounds(
                            sharedContentState = rememberSharedContentState(key = "${dessert.name}-bounds"),
                            animatedVisibilityScope = animatedVisibilityScope,
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
                                animatedVisibilityScope = animatedVisibilityScope,
                                boundsTransform = dessertBoundsTransition
                            )
                            .clickable {
                                onDessertClicked(dessert.id)
                            },
                        name = dessert.name,
                        image = dessert.image
                    )
                }
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
private fun DessertsScreenPreview() {
    SharedElementTransitionTheme {
        SharedTransitionLayout {
            AnimatedVisibility(true) {
                DessertsScreen(
                    modifier = Modifier.fillMaxSize(),
                    desserts = FakeDataProvider.getDesserts(),
                    animatedVisibilityScope = this,
                    sharedTransitionScope = this@SharedTransitionLayout,
                    onDessertClicked = {}
                )
            }
        }
    }
}