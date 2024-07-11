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
import androidx.compose.foundation.layout.Row
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
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jetpack.sharedelement.R
import com.jetpack.sharedelement.ui.theme.SharedElementTransitionTheme
import com.jetpack.sharedelement.ui.transition.sheet.components.AlbumImage
import com.jetpack.sharedelement.ui.transition.sheet.components.AlbumPlayControls

/**
 * Composable function for the main content displayed in the Sheet component.
 * Uses shared element transitions for smooth animations.
 */
@Composable
fun SharedTransitionScope.AlbumScreen(
    modifier: Modifier = Modifier,
    animatedVisibilityScope: AnimatedVisibilityScope
) {
    Card(
        modifier = modifier
    ) {
        Row(
            modifier = Modifier.padding(10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(
                space = 30.dp,
                alignment = Alignment.CenterHorizontally
            )
        ) {
            AlbumImage(
                modifier = Modifier
                    .sharedElement(
                        state = rememberSharedContentState(key = "image"),
                        animatedVisibilityScope = animatedVisibilityScope,
                        boundsTransform = sheetBoundsTransform
                    )
                    .size(height = 100.dp, width = 90.dp)
                    .clip(MaterialTheme.shapes.small.copy(all = CornerSize(20.dp)))
            )
            AlbumDetails()
            AlbumPlayControls(
                modifier = Modifier
                    .sharedBounds(
                        sharedContentState = rememberSharedContentState(key = "icons"),
                        animatedVisibilityScope = animatedVisibilityScope
                    ),
                playControlSize = 30.dp
            )
        }
    }
}


@Composable
private fun AlbumDetails(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
    ) {
        Text(
            text = stringResource(R.string.angel_beach),
            fontSize = 16.sp,
            fontFamily = FontFamily.Serif,
            fontWeight = FontWeight.ExtraBold,
            color = MaterialTheme.colors.onSurface
        )
        Text(
            text = stringResource(R.string.trilogy),
            fontSize = 14.sp,
            fontFamily = FontFamily.Serif,
            color = MaterialTheme.colors.onSurface
        )
    }
}

@Composable
@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
private fun AlbumScreenPreview() {
    SharedElementTransitionTheme {
        SharedTransitionLayout {
            AnimatedVisibility(true) {
                AlbumScreen(
                    animatedVisibilityScope = this
                )
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
private fun AlbumDetailsPreview() {
    SharedElementTransitionTheme {
        AlbumDetails()
    }
}
