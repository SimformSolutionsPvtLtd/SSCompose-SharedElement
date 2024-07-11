@file:OptIn(ExperimentalSharedTransitionApi::class)

package com.jetpack.sharedelement.ui.transition.without.navigation

import android.content.res.Configuration
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jetpack.sharedelement.R
import com.jetpack.sharedelement.data.FakeDataProvider
import com.jetpack.sharedelement.model.Coffee
import com.jetpack.sharedelement.ui.theme.SharedElementTransitionTheme

/**
 * Transformation for the shared element bounds.
 * Defines the tween animation for the shared element transitions.
 */
private val boundsTransform = { _: Rect, _: Rect ->
    tween<Rect>(500)
}

/**
 * Composable function for the shared element transition demo without navigation
 */
@Composable
fun TransitionWithoutNavigationScreen(
    modifier: Modifier = Modifier,
    onBack: () -> Unit
) {
    var showDetails by remember { mutableStateOf(false) }
    var selectedCoffeeId by remember { mutableIntStateOf(-1) }
    val coffeeList = remember(Unit) { FakeDataProvider.getCoffees() }

    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                title = {
                    Text(stringResource(R.string.shared_element_transition_without_navigation))
                },
                navigationIcon = {
                    IconButton(
                        onClick = onBack,
                        content = {
                            Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = null)
                        }
                    )
                }
            )
        }
    ) { paddingValues ->
        MainContent(
            modifier = Modifier.padding(paddingValues),
            coffees = coffeeList,
            showDetails = showDetails,
            selectedCoffeeId = selectedCoffeeId,
            onCoffeeClick = { coffee ->
                selectedCoffeeId = coffee.id
                showDetails = true
            },
            onBackClick = {
                showDetails = false
            }
        )
    }
}

/**
 * Composable function that sets up the navigation and shared element transitions
 * for the coffee preview and detail screens.
 */
@Composable
private fun MainContent(
    modifier: Modifier = Modifier,
    coffees: List<Coffee>,
    showDetails: Boolean,
    selectedCoffeeId: Int,
    onCoffeeClick: (Coffee) -> Unit,
    onBackClick: () -> Unit
) {
    SharedTransitionLayout(modifier = modifier) {
        AnimatedContent(
            targetState = showDetails,
            label = "transition"
        ) { targetState ->
            if (!targetState) {
                CoffeesScreen(
                    coffeeList = coffees,
                    animatedVisibilityScope = this,
                    onCoffeeClick = onCoffeeClick
                )
            } else {
                val selectedCoffee = coffees.find { it.id == selectedCoffeeId }
                selectedCoffee?.let { coffee ->
                    CoffeeDetailScreen(
                        modifier = Modifier
                            .padding(10.dp)
                            .clip(RoundedCornerShape(30.dp))
                            .background(Color.LightGray.copy(alpha = 0.5f))
                            // Adding shared element for the detailed view of the coffee item
                            // The sharedElement modifier is used to specify the shared element for the transition.
                            // - `rememberSharedContentState(key = coffee.id)` creates a state holder for the shared element with a unique key.
                            // - `animatedVisibilityScope` provides the scope for managing visibility changes during the transition.
                            // - `boundsTransform` defines the transformation applied to the bounds of the shared element during the transition.
                            .sharedElement(
                                state = rememberSharedContentState(key = coffee.id),
                                animatedVisibilityScope = this,
                                boundsTransform = boundsTransform
                            ),
                        coffee = coffee,
                        onBack = onBackClick
                    )
                }
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
private fun TransitionWithoutNavigationScreenPreview() {
    SharedElementTransitionTheme {
        TransitionWithoutNavigationScreen(onBack = { /* Click Action */})
    }
}

@Composable
@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
private fun MainContentPreview() {
    SharedElementTransitionTheme {
        MainContent(
            coffees = FakeDataProvider.getCoffees(),
            selectedCoffeeId = 0,
            showDetails = false,
            onCoffeeClick = {},
            onBackClick = {}
        )
    }
}

@Composable
@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
private fun MainContentWithShowDetailsPreview() {
    SharedElementTransitionTheme {
        MainContent(
            coffees = FakeDataProvider.getCoffees(),
            selectedCoffeeId = 0,
            showDetails = true,
            onCoffeeClick = {},
            onBackClick = {}
        )
    }
}
