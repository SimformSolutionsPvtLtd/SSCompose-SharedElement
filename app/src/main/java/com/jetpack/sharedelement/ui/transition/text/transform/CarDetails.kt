@file:OptIn(ExperimentalSharedTransitionApi::class)

package com.jetpack.sharedelement.ui.transition.text.transform

import android.content.res.Configuration
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jetpack.sharedelement.R
import com.jetpack.sharedelement.ui.theme.SharedElementTransitionTheme

/**
 * Composable function for displaying the details content screen.
 * Handles shared element transitions for details content items.
 */
@Composable
fun SharedTransitionScope.CarDetails(
    modifier: Modifier = Modifier,
    animatedVisibilityScope: AnimatedVisibilityScope,
) {
    Column(
        modifier = modifier
    ) {
        Image(
            modifier = Modifier
                .sharedElement(
                    state = rememberSharedContentState(key = "image"),
                    animatedVisibilityScope = animatedVisibilityScope,
                    boundsTransform = textBoundsTransform
                )
                .size(200.dp)
                .clip(CircleShape),
            painter = painterResource(id = R.drawable.car),
            contentDescription = stringResource(R.string.car),
        )
        Text(
            modifier = Modifier
                .sharedBounds(
                    sharedContentState = rememberSharedContentState(key = "title"),
                    animatedVisibilityScope = animatedVisibilityScope,
                    boundsTransform = animatedTextBoundsTransform
                ),
            text = stringResource(R.string.car),
            fontSize = 28.sp,
            color = MaterialTheme.colors.onSurface
        )
        Text(
            modifier = Modifier.skipToLookaheadSize(),
            color = MaterialTheme.colors.onSurface,
            text = stringResource(id = R.string.album_description),
        )
    }
}

@Composable
@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
private fun CarDetailsPreview() {
    SharedElementTransitionTheme {
        SharedTransitionLayout {
            AnimatedVisibility(true) {
                CarDetails(animatedVisibilityScope = this@AnimatedVisibility)
            }
        }
    }
}
