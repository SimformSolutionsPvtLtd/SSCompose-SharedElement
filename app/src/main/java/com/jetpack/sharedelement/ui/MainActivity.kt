package com.jetpack.sharedelement.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.jetpack.sharedelement.ui.theme.SharedElementTransitionTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SharedElementTransitionTheme {
                val navController = rememberNavController()
                MainScreen(
                    modifier = Modifier,
                    navController = navController
                )
            }
        }
    }
}