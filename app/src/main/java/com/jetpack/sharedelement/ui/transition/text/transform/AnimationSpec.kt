@file:OptIn(ExperimentalSharedTransitionApi::class, ExperimentalAnimationSpecApi::class)

package com.jetpack.sharedelement.ui.transition.text.transform

import androidx.compose.animation.BoundsTransform
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.core.ArcMode
import androidx.compose.animation.core.ExperimentalAnimationSpecApi
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.ui.geometry.Rect

private const val ANIMATION_DURATION_IN_MILLIS = 500

/**
 * BoundsTransform for general shared element transitions
 * Tween animation applied to transition between initial and target bounds
 */
val textBoundsTransform = BoundsTransform { _: Rect, _: Rect ->
    tween(
        durationMillis = ANIMATION_DURATION_IN_MILLIS,
        easing = FastOutSlowInEasing
    )
}

/**
 * BoundsTransform specifically designed for text elements with keyframes animation.
 * Defines how text elements transition between initial and target bounds with animation.
 * Uses keyframes to specify animation states over time.
 */
val animatedTextBoundsTransform = BoundsTransform { initialBounds, targetBounds ->
    keyframes {
        durationMillis = ANIMATION_DURATION_IN_MILLIS
        initialBounds at 0 using ArcMode.ArcBelow using FastOutSlowInEasing
        targetBounds at ANIMATION_DURATION_IN_MILLIS
    }
}

/**
 * Defines an animation for the Text component to enter the screen with a fade-in effect.
 * The animation duration is specified in milliseconds, and it uses a "FastOutSlowIn" easing function
 * for a smooth effect where the animation starts fast and slows down towards the end.
 */
val textEnterAnimation = fadeIn(
    tween(
        durationMillis = ANIMATION_DURATION_IN_MILLIS,
        easing = FastOutSlowInEasing
    )
)

/**
 * Defines an animation for the Text component to exit the screen with a fade-out effect.
 * Similar to the enter animation, the duration is specified in milliseconds, and it uses the same
 * "FastOutSlowIn" easing function, ensuring a consistent animation style for both entering and exiting.
 */
val textExitAnimation = fadeOut(
    tween(
        durationMillis = ANIMATION_DURATION_IN_MILLIS,
        easing = FastOutSlowInEasing
    )
)