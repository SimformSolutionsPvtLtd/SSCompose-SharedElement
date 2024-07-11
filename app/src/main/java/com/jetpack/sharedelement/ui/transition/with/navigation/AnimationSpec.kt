package com.jetpack.sharedelement.ui.transition.with.navigation

import androidx.compose.animation.core.tween
import androidx.compose.ui.geometry.Rect

private const val ANIMATION_DURATION_IN_MILLIS = 500

/**
 * Transformation for the shared element bounds.
 * Defines the tween animation for the shared element transitions.
 */
val albumBoundsTransform = { _: Rect, _: Rect ->
    tween<Rect>(durationMillis = ANIMATION_DURATION_IN_MILLIS)
}
