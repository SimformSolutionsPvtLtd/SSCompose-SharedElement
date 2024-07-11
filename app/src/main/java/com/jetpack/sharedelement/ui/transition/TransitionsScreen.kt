package com.jetpack.sharedelement.ui.transition

import android.content.Context
import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jetpack.sharedelement.R
import com.jetpack.sharedelement.model.Transition
import com.jetpack.sharedelement.navigation.Screens
import com.jetpack.sharedelement.ui.theme.SharedElementTransitionTheme

@Composable
fun TransitionsScreen(
    modifier: Modifier = Modifier,
    navigateTo: (String) -> Unit
) {
    val context = LocalContext.current
    val transitions = remember(Unit) { getTransitions(context) }

    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                title = {
                    Text(stringResource(R.string.shared_element_transition))
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier.padding(paddingValues)
        ) {
            transitions.forEach { transitionModel ->
                TransitionButton(
                    modifier = Modifier.padding(12.dp).fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    text = transitionModel.name,
                    onClick = {
                        navigateTo(transitionModel.screen.route)
                    }
                )
            }
        }
    }
}

@Composable
private fun TransitionButton(
    modifier: Modifier = Modifier,
    shape: RoundedCornerShape,
    text: String,
    onClick: () -> Unit
) {
    Button(
        modifier = modifier,
        shape = shape,
        onClick = onClick
    ) {
        Text(
            modifier = Modifier.padding(10.dp),
            text = text,
            color = Color.White,
        )
    }
}

private fun getTransitions(context: Context): List<Transition> = listOf(
    Transition(
        name = context.getString(R.string.set_with_navigation),
        screen = Screens.TransitionWithNavigationScreen
    ),
    Transition(
        name = context.getString(R.string.set_without_navigation),
        screen = Screens.TransitionWithoutNavigationScreen
    ),
    Transition(
        name = context.getString(R.string.text_transform_animation),
        screen = Screens.TransitionWithTextTransformScreen
    ),
    Transition(
        name = context.getString(R.string.animated_visibility_shared_element),
        screen = Screens.TransitionWithAnimatedVisibilityScreen
    )
)

@Composable
@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
private fun TransitionButtonPreview() {
    SharedElementTransitionTheme {
        TransitionButton(
            shape = RoundedCornerShape(12.dp),
            text = stringResource(R.string.shared_element_transition_with_navigation),
            onClick = { /* Click Action */ }
        )
    }
}

@Composable
@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
private fun TransitionsScreenPreview() {
    SharedElementTransitionTheme {
        TransitionsScreen(navigateTo = { /* Click Action */ })
    }
}