@file:OptIn(ExperimentalSharedTransitionApi::class)

package com.jetpack.sharedelement.ui.transition.without.navigation

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
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jetpack.sharedelement.R
import com.jetpack.sharedelement.data.FakeDataProvider
import com.jetpack.sharedelement.model.Coffee
import com.jetpack.sharedelement.ui.transition.components.InformationPanel
import com.jetpack.sharedelement.ui.theme.SharedElementTransitionTheme

/**
 * Composable function that displays the details of a selected coffee item.
 */
@Composable
fun SharedTransitionScope.CoffeeDetailScreen(
    modifier: Modifier = Modifier,
    coffee: Coffee,
    onBack: () -> Unit
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        CoffeeDetailHeader(
            cover = painterResource(id = coffee.image),
            onBackClick = onBack
        )
        CoffeeDetailDescription(
            modifier = Modifier.padding(10.dp),
            title = coffee.name,
            description = coffee.description
        )
    }
}

/**
 * Composable function that displays the header of the coffee details view.
 */
@Composable
private fun CoffeeDetailHeader(
    modifier: Modifier = Modifier,
    cover: Painter,
    onBackClick: () -> Unit
) {
    Box(
        modifier = modifier
    ) {
        Image(
            modifier = Modifier
                .size(400.dp)
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
 * Composable function that displays the description of the selected coffee item.
 */
@Composable
private fun SharedTransitionScope.CoffeeDetailDescription(
    modifier: Modifier = Modifier,
    title: String,
    description: String
) {
    Column(
        modifier = modifier
    ) {
        Text(
            text = title,
            fontFamily = FontFamily.Monospace,
            fontWeight = FontWeight.ExtraBold,
            fontSize = 26.sp,
            color = MaterialTheme.colors.onSurface,
        )
        InformationPanel(
            modifier = Modifier.padding(top = 20.dp),
            description = description
        )
    }
}

@Composable
@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
private fun CoffeeDetailContentPreview() {
    val coffee = FakeDataProvider.getCoffees()[0]

    SharedElementTransitionTheme {
        SharedTransitionLayout {
            CoffeeDetailScreen(
                coffee = coffee
            ) { /* Click Action */ }
        }
    }
}

@Composable
@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
private fun CoffeeDetailHeaderPreview() {
    SharedElementTransitionTheme {
        CoffeeDetailHeader(
            cover = painterResource(R.drawable.ic_espresso),
            onBackClick = { /* Click Action */ }
        )
    }
}

@Composable
@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
private fun CoffeeDetailDescriptionPreview() {
    SharedElementTransitionTheme {
        SharedTransitionLayout {
            CoffeeDetailDescription(
                modifier = Modifier.fillMaxWidth(),
                title = "Doppio",
                description = "Espresso with double the amount of milk."
            )
        }
    }
}