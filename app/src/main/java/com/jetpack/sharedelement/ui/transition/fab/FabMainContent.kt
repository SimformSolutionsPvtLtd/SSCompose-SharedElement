@file:OptIn(ExperimentalSharedTransitionApi::class)

package com.jetpack.sharedelement.ui.transition.fab

import android.content.res.Configuration
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkHorizontally
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jetpack.sharedelement.ui.theme.SharedElementTransitionTheme

/**
 * Composable function for the main content displayed in the FAB component.
 * Uses shared element transitions for smooth animations.
 */
@Composable
fun SharedTransitionScope.FabMainContent(
    modifier: Modifier = Modifier,
    animatedVisibilityScope: AnimatedVisibilityScope,
    onShowDetails: () -> Unit
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.End,
        verticalAlignment = Alignment.Bottom
    ) {
        Card(
            modifier = Modifier
                .padding(end = 30.dp, bottom = 40.dp)
                .size(80.dp)
                .sharedBounds(
                    sharedContentState = rememberSharedContentState(key = "card_bounds"),
                    animatedVisibilityScope = animatedVisibilityScope,
                    enter = expandHorizontally() + expandVertically(),
                    exit = shrinkHorizontally() + shrinkVertically(),
                    boundsTransform = fabBoundsTransform
                )
                .clip(MaterialTheme.shapes.medium.copy(all = CornerSize(20.dp)))
                .clickable(onClick = onShowDetails),
            backgroundColor = Color.Cyan,
            elevation = 0.dp
        ) {
            Icon(
                modifier = Modifier
                    .padding(20.dp)
                    .sharedElement(
                        state = rememberSharedContentState(key = "card_icon"),
                        animatedVisibilityScope = animatedVisibilityScope,
                        boundsTransform = fabBoundsTransform
                    ),
                imageVector = Icons.Filled.Edit,
                contentDescription = "",
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
private fun FabMainContentPreview() {
    SharedElementTransitionTheme {
        SharedTransitionLayout {
            AnimatedVisibility(true) {
                FabMainContent(
                    modifier = Modifier.fillMaxSize(),
                    animatedVisibilityScope = this,
                    onShowDetails = {}
                )
            }
        }
    }
}