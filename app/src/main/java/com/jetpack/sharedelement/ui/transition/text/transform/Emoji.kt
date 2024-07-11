@file:OptIn(ExperimentalSharedTransitionApi::class)

package com.jetpack.sharedelement.ui.transition.text.transform

import android.content.res.Configuration
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jetpack.sharedelement.R
import com.jetpack.sharedelement.ui.theme.SharedElementTransitionTheme

/**
 * Composable function for displaying the main content screen.
 * Handles shared element transitions for main content items.
 */
@Composable
fun SharedTransitionScope.Emoji(
    modifier: Modifier = Modifier,
    animatedVisibilityScope: AnimatedVisibilityScope,
) {
    Row(
        modifier = modifier
    ) {
        Image(
            modifier = Modifier
                .sharedElement(
                    state = rememberSharedContentState(key = "image"),
                    animatedVisibilityScope = animatedVisibilityScope,
                    boundsTransform = boundsTransform
                )
                .size(100.dp)
                .clip(CircleShape),
            painter = painterResource(id = R.drawable.dp10),
            contentDescription = stringResource(R.string.emojis),
            contentScale = ContentScale.Crop
        )
        Text(
            modifier = Modifier
                .sharedBounds(
                    sharedContentState = rememberSharedContentState(key = "title"),
                    animatedVisibilityScope = animatedVisibilityScope,
                    boundsTransform = textBoundsTransform
                ),
            text = stringResource(R.string.emojis),
            fontSize = 21.sp,
            color = MaterialTheme.colors.onSurface
        )
    }
}

@Composable
@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
private fun EmojiPreview() {
    SharedElementTransitionTheme {
        SharedTransitionLayout {
            AnimatedVisibility(true) {
                Emoji(
                    modifier = Modifier.fillMaxWidth(),
                    animatedVisibilityScope = this@AnimatedVisibility
                )
            }
        }
    }
}
