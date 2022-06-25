package com.islam.music

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.islam.music.ui.*
import com.islam.music.ui.theme.MusicTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MusicTheme {
                val navController = rememberNavController()
                val musicNavigation = MusicNavigation(navController)
                NavHost(navController = navController, startDestination = "mainScreen") {
                    composable("mainScreen") {
                        MainScreen(navController)
                    }
                    composable("searchScreen") {
                        SearchScreen(navController)
                    }
                    composable("albumDetailsScreen") {
                        AlbumDetailsScreen(navController)
                    }
                    composable("topAlbumsScreen") {
                        TopAlbumsScreen(navController)
                    }
                }
            }


        }
    }
}





