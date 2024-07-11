@file:OptIn(ExperimentalSharedTransitionApi::class)

package com.jetpack.sharedelement.ui.transition.fab

import android.content.res.Configuration
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jetpack.sharedelement.R
import com.jetpack.sharedelement.data.FakeDataProvider
import com.jetpack.sharedelement.model.Profile
import com.jetpack.sharedelement.ui.theme.SharedElementTransitionTheme

/**
 * Composable function displaying the details content within the FAB component.
 * Utilizes shared element transitions for smooth animations.
 */
@Composable
fun SharedTransitionScope.FabDetailsContent(
    modifier: Modifier = Modifier,
    animatedVisibilityScope: AnimatedVisibilityScope,
    profiles: List<Profile>,
    onBack: () -> Unit
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.BottomEnd
    ) {
        LazyColumn(
            modifier = Modifier
                .padding(end = 30.dp, bottom = 40.dp)
                .width(160.dp)
                .clip(MaterialTheme.shapes.small.copy(all = CornerSize(20.dp)))
                .background(Color.LightGray.copy(alpha = 0.5f))
                .clickable(onClick = onBack)
        ) {
            items(profiles) { profile: Profile ->
                ProfileItem(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    profile = profile
                )
            }
            item {
                ProfileFooter(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(80.dp)
                        .background(Color.Cyan.copy(0.5f))
                        .clickable(onClick = onBack)
                        .sharedBounds(
                            sharedContentState = rememberSharedContentState(key = "card_bounds"),
                            animatedVisibilityScope = animatedVisibilityScope,
                            enter = fabEnterAnimation,
                            exit = fabExitAnimation,
                            boundsTransform = fabBoundsTransform
                        )
                )
            }
        }
    }
}

/**
 * Composable function displaying the footer of the profile list.
 * Utilizes shared element transitions for smooth animations.
 */
@Composable
private fun ProfileFooter(
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(
            space = 20.dp,
            alignment = Alignment.CenterHorizontally
        ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            modifier = Modifier.size(40.dp),
            imageVector = Icons.Filled.Edit,
            contentDescription = "",
            contentScale = ContentScale.Crop
        )
        Text(
            text = stringResource(R.string.edit),
            fontSize = 14.sp,
            color = MaterialTheme.colors.onSurface
        )
    }
}

/**
 * Composable function displaying a individual profile item.
 * Utilizes shared element transitions for smooth animations.
 */
@Composable
private fun ProfileItem(
    modifier: Modifier,
    profile: Profile
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(
            space = 15.dp,
            alignment = Alignment.CenterHorizontally
        ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape),
            painter = painterResource(id = profile.image),
            contentDescription = "",
            contentScale = ContentScale.Crop
        )
        Text(
            text = profile.name,
            fontSize = 14.sp,
            color = MaterialTheme.colors.onSurface
        )
    }
}

@Composable
@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
private fun FabDetailsContentPreview() {
    SharedElementTransitionTheme {
        SharedTransitionLayout {
            AnimatedVisibility(true) {
                FabDetailsContent(
                    modifier = Modifier.fillMaxSize(),
                    animatedVisibilityScope = this@AnimatedVisibility,
                    profiles = FakeDataProvider.getFabProfiles(),
                    onBack = { /* Handle Click Action */ }
                )
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
private fun ProfileHeaderPreview() {
    SharedElementTransitionTheme {
        ProfileFooter(
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
private fun ProfileItemPreview() {
    SharedElementTransitionTheme {
        ProfileItem(
            modifier = Modifier.fillMaxWidth(),
            profile = FakeDataProvider.getFabProfiles().first()
        )
    }
}
