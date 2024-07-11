@file:OptIn(ExperimentalSharedTransitionApi::class)

package com.jetpack.sharedelement.ui.transition.sheet

import android.content.res.Configuration
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jetpack.sharedelement.R
import com.jetpack.sharedelement.ui.theme.SharedElementTransitionTheme
import com.jetpack.sharedelement.ui.transition.sheet.components.AlbumImage
import com.jetpack.sharedelement.ui.transition.sheet.components.AlbumPlayControls

/**
 * Composable function displaying the details content within the Sheet component.
 * Utilizes shared element transitions for smooth animations.
 */
@Composable
fun SharedTransitionScope.AlbumDetailScreen(
    modifier: Modifier = Modifier,
    animatedVisibilityScope: AnimatedVisibilityScope,
) {
    Card(
        modifier = modifier
    ) {
        Column(
            modifier = Modifier
                .padding(15.dp)
                .sharedBounds(
                    sharedContentState = rememberSharedContentState(key = "bounds"),
                    animatedVisibilityScope = animatedVisibilityScope,
                    boundsTransform = sheetBoundsTransform
                ),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(20.dp),
        ) {
            AlbumImage(
                modifier = Modifier
                    .size(300.dp)
                    .sharedElement(
                        state = rememberSharedContentState(key = "image"),
                        animatedVisibilityScope = animatedVisibilityScope,
                        boundsTransform = sheetBoundsTransform
                    )
                    .clip(MaterialTheme.shapes.small.copy(all = CornerSize(20.dp)))
            )
            Text(
                text = stringResource(R.string.angel_beach),
                fontWeight = FontWeight.ExtraBold,
                fontSize = 28.sp
            )
            Text(
                text = stringResource(R.string.trilogy),
                fontSize = 18.sp
            )
            AlbumPlayControls(
                modifier = Modifier
                    .sharedBounds(
                        sharedContentState = rememberSharedContentState(key = "icons"),
                        animatedVisibilityScope = animatedVisibilityScope
                    ),
                playControlSize = 80.dp
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
private fun AlbumDetailScreenPreview() {
    SharedElementTransitionTheme {
        SharedTransitionLayout {
            AnimatedVisibility(visible = true) {
                AlbumDetailScreen(
                    animatedVisibilityScope = this,
                )
            }
        }
    }
}