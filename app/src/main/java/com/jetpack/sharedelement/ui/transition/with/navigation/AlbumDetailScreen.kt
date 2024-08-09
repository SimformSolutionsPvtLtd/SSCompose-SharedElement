@file:OptIn(ExperimentalSharedTransitionApi::class)

package com.jetpack.sharedelement.ui.transition.with.navigation

import android.content.res.Configuration
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jetpack.sharedelement.R
import com.jetpack.sharedelement.data.FakeDataProvider
import com.jetpack.sharedelement.model.Album
import com.jetpack.sharedelement.ui.transition.components.InformationPanel
import com.jetpack.sharedelement.ui.theme.SharedElementTransitionTheme

/**
 * Composable function for displaying the detailed view of an album.
 * It includes shared element transition for the album cover image and additional album details.
 */
@Composable
fun SharedTransitionScope.AlbumDetailScreen(
    modifier: Modifier = Modifier,
    album: Album,
    onBackClick: () -> Unit
) {
    Column(
        modifier = modifier
    ) {
        AlbumDetailHeader(
            cover = painterResource(id = album.cover),
            onBackClick = onBackClick
        )
        AlbumDetailInfo(
            modifier = Modifier.padding(20.dp),
            title = album.title,
            subtitle = "${album.author}, ${album.year}"
        )
        AlbumDetailDescription(
            modifier = Modifier.padding(10.dp)
        )
    }
}

/**
 * Composable function for displaying the album detail header with the album cover image.
 */
@Composable
private fun AlbumDetailHeader(
    modifier: Modifier = Modifier,
    cover: Painter,
    onBackClick: () -> Unit
) {
    Box(
        modifier = modifier
    ) {
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .height(400.dp)
                .clip(MaterialTheme.shapes.small.copy(all = CornerSize(25.dp))),
            painter = cover,
            contentDescription = null,
            contentScale = ContentScale.Crop,
        )
        Box(
            modifier = Modifier
                .padding(start = 10.dp, top = 10.dp)
                .clip(CircleShape)
                .background(Color.White)
                .clickable(onClick = onBackClick)
        ) {
            Icon(
                modifier = Modifier
                    .size(50.dp)
                    .padding(10.dp),
                imageVector = Icons.AutoMirrored.Outlined.ArrowBack,
                tint = Color.Black,
                contentDescription = null
            )
        }
    }
}

/**
 * Composable function for displaying the album information such as title, author, and year.
 */
@Composable
private fun AlbumDetailInfo(
    modifier: Modifier = Modifier,
    title: String,
    subtitle: String
) {
    Column(
        modifier = modifier
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(4.dp, Alignment.CenterHorizontally),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = title,
                    fontFamily = FontFamily.Serif,
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 24.sp,
                    color = MaterialTheme.colors.onSurface
                )
                Text(
                    text = subtitle,
                    fontSize = 20.sp,
                    color = MaterialTheme.colors.onSurface
                )
            }
            IconButton(
                modifier = Modifier
                    .clip(CircleShape)
                    .background(Color.Magenta.copy(alpha = 0.3f)),
                onClick = { /* Handle play action */ }
            ) {
                Icon(
                    modifier = Modifier
                        .size(50.dp),
                    imageVector = Icons.Filled.PlayArrow,
                    tint = MaterialTheme.colors.onSurface,
                    contentDescription = null,
                )
            }
        }
    }
}

/**
 * Composable function for displaying the album description.
 * Uses shared element transition scope to ensure smooth transitions.
 */
@Composable
private fun SharedTransitionScope.AlbumDetailDescription(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
    ) {
        InformationPanel(
            modifier = Modifier.padding(horizontal = 10.dp),
            description = stringResource(R.string.album_description)
        )
    }
}

@Composable
@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
private fun AlbumDetailPreview() {
    val album = FakeDataProvider.getAlbums()[0]

    SharedElementTransitionTheme {
        SharedTransitionLayout {
            AlbumDetailScreen(
                album = album
            ) { /* Click Action */}
        }
    }
}

@Composable
@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
private fun AlbumDetailHeaderPreview() {
    SharedElementTransitionTheme {
        AlbumDetailHeader(
            cover = painterResource(R.drawable.img_album_01)
        ) { /* Click Action */ }
    }
}

@Composable
@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
private fun AlbumDetailInfoPreview() {
    SharedElementTransitionTheme {
        AlbumDetailInfo(
            modifier = Modifier.fillMaxWidth(),
            title = "It happened quiet",
            subtitle = "Aurora, 2018"
        )
    }
}

@Composable
@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
private fun AlbumDetailDescriptionPreview() {
    SharedElementTransitionTheme {
        SharedTransitionLayout {
            AlbumDetailDescription()
        }
    }
}
