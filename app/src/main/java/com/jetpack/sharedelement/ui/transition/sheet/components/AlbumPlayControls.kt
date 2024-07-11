package com.jetpack.sharedelement.ui.transition.sheet.components

import android.content.res.Configuration
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.jetpack.sharedelement.R
import com.jetpack.sharedelement.ui.theme.SharedElementTransitionTheme

@Composable
fun AlbumPlayControls(
    modifier: Modifier = Modifier,
    playControlSize: Dp
) {
    Row(
        modifier = modifier
    ) {
        Icon(
            modifier = Modifier.size(playControlSize),
            painter = painterResource(R.drawable.baseline_skip_previous_24),
            contentDescription = stringResource(R.string.app_name),
            tint = MaterialTheme.colors.onSurface
        )
        Icon(
            modifier = Modifier.size(playControlSize),
            imageVector = Icons.Filled.PlayArrow,
            contentDescription = stringResource(R.string.app_name),
            tint = MaterialTheme.colors.onSurface
        )
        Icon(
            modifier = Modifier.size(playControlSize),
            painter = painterResource(R.drawable.baseline_skip_next_24),
            contentDescription = stringResource(R.string.app_name),
            tint = MaterialTheme.colors.onSurface
        )
    }
}

@Composable
@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
private fun AlbumPlayControlsPreview() {
    SharedElementTransitionTheme {
        AlbumPlayControls(playControlSize = 20.dp)
    }
}
