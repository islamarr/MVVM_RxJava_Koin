package com.islam.music.ui

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.islam.music.ui.theme.MusicTheme

@Composable
fun MainScreen(navController: NavController) {
    Scaffold(
        topBar = {
            DefaultAppBar(
                onSearchClicked = {
                    navController.navigate("searchScreen")
                }
            )
        }
    ) {}

}


@Composable
fun DefaultAppBar(onSearchClicked: () -> Unit) {
    TopAppBar(
        title = {
            Text(
                text = "Home"
            )
        },
        actions = {
            IconButton(
                onClick = { onSearchClicked() }
            ) {
                Icon(
                    imageVector = Icons.Filled.Search,
                    contentDescription = "Search Icon",
                    tint = Color.White
                )
            }
        }
    )
}


@Preview(showBackground = true, widthDp = 420)
@Composable
fun DefaultPreview() {
    MusicTheme {
        MainScreen(rememberNavController())
    }
}