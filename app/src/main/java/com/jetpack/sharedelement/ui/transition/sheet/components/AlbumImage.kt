package com.jetpack.sharedelement.ui.transition.sheet.components

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.jetpack.sharedelement.R
import com.jetpack.sharedelement.ui.theme.SharedElementTransitionTheme

@Composable
fun AlbumImage(modifier: Modifier = Modifier) {
    Image(
        modifier = modifier,
        painter = painterResource(id = R.drawable.ic_post_image_6),
        contentDescription = stringResource(R.string.app_name),
        contentScale = ContentScale.Crop
    )
}

@Composable
@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
private fun AlbumImagePreview() {
    SharedElementTransitionTheme {
        AlbumImage()
    }
}