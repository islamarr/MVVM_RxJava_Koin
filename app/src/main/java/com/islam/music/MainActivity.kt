package com.islam.music

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.islam.music.features.search.presentation.viewmodel.SearchViewModel
import com.islam.music.ui.*
import com.islam.music.ui.theme.MusicTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MusicTheme {
                val navController = rememberNavController()
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





