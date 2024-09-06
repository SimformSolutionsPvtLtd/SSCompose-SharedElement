@file:OptIn(ExperimentalSharedTransitionApi::class)

package com.jetpack.sharedelement.ui.transition.animated.visibility

import android.content.res.Configuration
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jetpack.sharedelement.R
import com.jetpack.sharedelement.data.FakeDataProvider
import com.jetpack.sharedelement.model.Dessert
import com.jetpack.sharedelement.ui.theme.SharedElementTransitionTheme

/**
 * Composable function for shared element transition demo with AnimatedVisibility.
 */
@Composable
fun TransitionWithAnimatedVisibilityScreen(
    modifier: Modifier = Modifier,
    onBack: () -> Unit
) {
    var selectedDessert by remember { mutableStateOf<Dessert?>(null) }
    val desserts = remember(Unit) { FakeDataProvider.getDesserts() }

    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                title = {
                    Text(stringResource(R.string.animated_visibility_shared_element))
                },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = null)
                    }
                }
            )
        }
    ) { paddingValues ->
        MainContent(
            modifier = Modifier.padding(paddingValues),
            desserts = desserts,
            selectedDessert = selectedDessert,
            onSelectedDessert = { dessert ->
                selectedDessert = dessert
            },
            onSaveClick = {
                selectedDessert = null
            }
        )
    }
}

/**
 * Composable function for displaying a list of desserts with shared element transitions.
 * Applies animations and blur effect when a dessert is selected.
 */
@Composable
private fun MainContent(
    modifier: Modifier = Modifier,
    desserts: List<Dessert>,
    selectedDessert: Dessert?,
    onSelectedDessert: (Dessert) -> Unit,
    onSaveClick: () -> Unit
) {
    SharedTransitionLayout(
        modifier = modifier
    ) {
        LazyColumn(
            modifier = Modifier
                .background(Color.LightGray.copy(alpha = 0.5f))
                .then(if (selectedDessert != null) Modifier.blur(10.dp) else Modifier)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            itemsIndexed(desserts) { _, dessert ->
                DessertItem(
                    modifier = Modifier.animateItem(
                        placementSpec = sharedElementTransitionSpec(),
                        fadeOutSpec = sharedElementTransitionSpec(),
                        fadeInSpec = sharedElementTransitionSpec()
                    ),
                    dessert = dessert,
                    visible = selectedDessert != dessert,
                    onClick = {
                        onSelectedDessert(dessert)
                    }
                )
            }
        }
        if (selectedDessert != null) {
            DessertDetailScreen(
                modifier = Modifier.fillMaxSize(),
                dessert = selectedDessert,
                onSaveClick = onSaveClick
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
private fun MainContentPreview() {
    SharedElementTransitionTheme {
        MainContent(
            desserts = FakeDataProvider.getDesserts(),
            selectedDessert = null,
            onSelectedDessert = { /* Handle Click Action */ },
            onSaveClick = { /* Handle Click Action */ }
        )
    }
}

@Composable
@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
private fun TransitionWithAnimatedVisibilityScreenPreview() {
    SharedElementTransitionTheme {
        TransitionWithAnimatedVisibilityScreen(
            onBack = { /* Handle Click Action */ }
        )
    }
}