@file:OptIn(ExperimentalSharedTransitionApi::class)

package com.jetpack.sharedelement.ui.transition.animated.visibility

import android.content.res.Configuration
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jetpack.sharedelement.R
import com.jetpack.sharedelement.data.FakeDataProvider
import com.jetpack.sharedelement.model.Dessert
import com.jetpack.sharedelement.ui.theme.SharedElementTransitionTheme
import com.jetpack.sharedelement.ui.transition.animated.visibility.components.DesertContents

/**
 * Composable function for displaying the details content screen for a selected desert.
 * Handles shared element transitions for details content items.
 */
@Composable
fun SharedTransitionScope.DessertDetailScreen(
    modifier: Modifier = Modifier,
    dessert: Dessert,
    onSaveClick: () -> Unit
) {
    AnimatedContent(
        modifier = modifier,
        targetState = dessert,
        transitionSpec = {
            fadeIn() togetherWith fadeOut()
        },
        label = "DessertEditDetails"
    ) { targetDessert ->
        Box(
            modifier = Modifier
                .background(Color.Black.copy(alpha = 0.5f)),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .clip(shape = MaterialTheme.shapes.small.copy(all = CornerSize(15.dp)))
                    .background(
                        color = MaterialTheme.colors.onPrimary,
                        shape = MaterialTheme.shapes.small.copy(all = CornerSize(15.dp))
                    )
            ) {
                DesertContents(
                    modifier = Modifier
                        .sharedElement(
                            state = rememberSharedContentState(key = targetDessert.name),
                            animatedVisibilityScope = this@AnimatedContent,
                            clipInOverlayDuringTransition = OverlayClip(
                                clipShape = MaterialTheme.shapes.small.copy(all = CornerSize(15.dp))
                            )
                        )
                        .clickable(onClick = onSaveClick),
                    name = targetDessert.name,
                    image = targetDessert.image
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp, end = 8.dp),
                    horizontalArrangement = Arrangement.End
                ) {
                    TextButton(onClick = onSaveClick) {
                        Text(text = stringResource(R.string.save_changes))
                    }
                }
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
private fun DessertDetailScreenPreview() {
    val dessert = FakeDataProvider.getDesserts()[0]

    SharedElementTransitionTheme {
        SharedTransitionLayout {
            DessertDetailScreen(
                modifier = Modifier.fillMaxSize(),
                dessert = dessert,
                onSaveClick = { /* Handle Click Action */ }
            )
        }
    }
}