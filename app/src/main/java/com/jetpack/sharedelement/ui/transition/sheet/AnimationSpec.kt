@file:OptIn(ExperimentalSharedTransitionApi::class)

package com.jetpack.sharedelement.ui.transition.sheet

import androidx.compose.animation.BoundsTransform
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.ui.geometry.Rect

private const val ANIMATION_DURATION_IN_MILLIS = 700

/**
 * BoundsTransform for general shared element transitions
 * Tween animation applied to transition between initial and target bounds
 */
val sheetBoundsTransform = BoundsTransform { _: Rect, _: Rect ->
    tween(
        durationMillis = ANIMATION_DURATION_IN_MILLIS,
        easing = FastOutSlowInEasing
    )
}
