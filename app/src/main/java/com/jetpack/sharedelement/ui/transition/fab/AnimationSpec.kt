@file:OptIn(ExperimentalSharedTransitionApi::class)

package com.jetpack.sharedelement.ui.transition.fab

import androidx.compose.animation.BoundsTransform
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.ui.geometry.Rect

private const val ANIMATION_DURATION_IN_MILLIS = 500

/**
 * BoundsTransform for general shared element transitions
 * Tween animation applied to transition between initial and target bounds
 */
val fabBoundsTransform = BoundsTransform { _: Rect, _: Rect ->
    tween(
        durationMillis = ANIMATION_DURATION_IN_MILLIS,
        easing = FastOutSlowInEasing
    )
}

/**
 * Defines an animation for the Floating Action Button (FAB) to enter the screen with a fade-in effect.
 * The animation duration is specified in milliseconds, and it uses a "FastOutSlowIn" easing function
 * for a smooth effect where the animation starts fast and slows down towards the end.
 */
val fabEnterAnimation = fadeIn(
    tween(
        durationMillis = ANIMATION_DURATION_IN_MILLIS,
        easing = FastOutSlowInEasing
    )
)

/**
 * Defines an animation for the Floating Action Button (FAB) to exit the screen with a fade-out effect.
 * Similar to the enter animation, the duration is specified in milliseconds, and it uses the same
 * "FastOutSlowIn" easing function, ensuring a consistent animation style for both entering and exiting.
 */
val fabExitAnimation = fadeOut(
    tween(
        durationMillis = ANIMATION_DURATION_IN_MILLIS,
        easing = FastOutSlowInEasing
    )
)