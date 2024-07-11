@file:OptIn(ExperimentalSharedTransitionApi::class)

package com.jetpack.sharedelement.ui.transition.with.navigation

import android.content.res.Configuration
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jetpack.sharedelement.data.FakeDataProvider
import com.jetpack.sharedelement.model.Album
import com.jetpack.sharedelement.ui.theme.SharedElementTransitionTheme

/**
 * Composable function for displaying the album preview content.
 * It contains a grid of album covers that users can click to see details.
 */
@Composable
fun SharedTransitionScope.AlbumsScreen(
    modifier: Modifier = Modifier,
    albums: List<Album>,
    animatedVisibilityScope: AnimatedVisibilityScope,
    onAlbumClick: (Album) -> Unit
) {
    LazyVerticalGrid(
        modifier = modifier,
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(10.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        itemsIndexed(albums) { _, album ->
            AlbumItem(
                modifier = Modifier
                    // Adding shared element for album cover
                    // The sharedElement modifier is used to specify the shared element for transition.
                    // - `rememberSharedContentState(key = albumId)` creates a state holder for the shared element with a unique key.
                    // - `animatedVisibilityScope` provides the scope for managing visibility changes during the transition.
                    // - `boundsTransform` defines the transformation applied to the bounds of the shared element during the transition.
                    .sharedElement(
                        state = rememberSharedContentState(key = album.id),
                        animatedVisibilityScope = animatedVisibilityScope,
                        boundsTransform = boundsTransform
                    ),
                album = album,
                onClick = { 
                    onAlbumClick(album)
                }
            )
        }
    }
}

/**
 * Composable function for displaying each album item in the preview grid.
 * It adds a shared element transition effect to the album cover image.
 */
@Composable
private fun AlbumItem(
    modifier: Modifier = Modifier,
    album: Album,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
            .clickable(onClick = onClick)
    ) {
        Image(
            modifier = Modifier
                .aspectRatio(1f)
                .clip(RoundedCornerShape(10.dp)),
            painter = painterResource(id = album.cover),
            contentDescription = "",
            contentScale = ContentScale.Crop,
        )
    }
}

@Composable
@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
private fun AlbumsScreenPreview() {
    val albums = FakeDataProvider.getAlbums()

    SharedElementTransitionTheme {
        SharedTransitionLayout {
            AnimatedVisibility(true) {
                AlbumsScreen (
                    albums = albums,
                    animatedVisibilityScope = this
                ) { /* Click Action */ }
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
private fun AlbumItemPreview() {
    val album = FakeDataProvider.getAlbums()[0]

    SharedElementTransitionTheme {
        SharedTransitionLayout {
            AlbumItem(
                album = album,
            ) { /* Click Action */ }
        }
    }
}
