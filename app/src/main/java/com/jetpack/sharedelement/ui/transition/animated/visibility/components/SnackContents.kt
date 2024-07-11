package com.jetpack.sharedelement.ui.transition.animated.visibility.components

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jetpack.sharedelement.R
import com.jetpack.sharedelement.ui.theme.SharedElementTransitionTheme

/**
 * Composable function for displaying the contents of a snack item.
 * Includes an image and the name of the snack.
 */
@Composable
fun SnackContents(
    modifier: Modifier = Modifier,
    name: String,
    image: Int
) {
    Column(
        modifier = modifier
    ) {
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(20f / 9f),
            painter = painterResource(id = image),
            contentScale = ContentScale.Crop,
            contentDescription = null
        )
        Text(
            modifier = Modifier
                .wrapContentWidth()
                .padding(8.dp),
            text = name,
            color = MaterialTheme.colors.onSurface,
            style = MaterialTheme.typography.subtitle1
        )
    }
}

@Composable
@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
private fun SnackContentsPreview() {
    SharedElementTransitionTheme {
        SnackContents(
            name = "Cupcake",
            image = R.drawable.cupcake
        )
    }
}