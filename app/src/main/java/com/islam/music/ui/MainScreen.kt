package com.islam.music.ui

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.islam.music.common.NetworkImageComponentGlide
import com.islam.music.features.search.domain.entites.Artist
import com.islam.music.features.top_albums.domain.entites.Album
import com.islam.music.ui.theme.MusicTheme

val albList = listOf( //TODO replace with room data
    Album(
        Artist(name = "artist_1"),
        listOf(com.islam.music.features.search.domain.entites.Image("https://i.picsum.photos/id/828/200/200.jpg?hmac=XDYHUvU1Ha9LQrkNk3svII_91vwnQqo8C0yWMqCt6V8")),
        "1",
        "album_1"
    ),
    Album(
        Artist(name = "artist_2"),
        listOf(com.islam.music.features.search.domain.entites.Image("https://i.picsum.photos/id/860/200/200.jpg?hmac=xEnSgZhxWVFOWiVCBQzYpKUH7S5nFb7-QTZ8Hfqwq4M")),
        "2",
        "album_2"
    ),
    Album(
        Artist(name = "artist_3"),
        listOf(com.islam.music.features.search.domain.entites.Image("https://i.picsum.photos/id/969/200/200.jpg?hmac=p4_e12QQOwtyNXXwJjJs_2kwmu87KZGqAhiUV8goVos")),
        "3",
        "album_3"
    )
)

@Composable
fun MainScreen(navController: NavController) {
    Scaffold(
        topBar = {
            DefaultAppBar(
                onSearchClicked = {
                    navController.navigate("searchScreen")
                }
            )
        },
        content = { LazyColumnItemsScrollableComponent(albList) },
    )
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

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun LazyColumnItemsScrollableComponent(albumList: List<Album>) {
    LazyColumn(modifier = Modifier.fillMaxHeight()) {
        items(items = albumList, itemContent = { album ->
            Card(
                shape = RoundedCornerShape(4.dp),
                backgroundColor = Color.White,
                modifier = Modifier
                    .fillParentMaxWidth()
                    .padding(8.dp)
            ) {
                ListItem(text = {
                    Text(
                        text = album.albumName,
                        style = TextStyle(
                            fontFamily = FontFamily.Serif, fontSize = 25.sp,
                            fontWeight = FontWeight.Bold
                        )
                    )
                }, secondaryText = {
                    Text(
                        text = "${album.artist.name}",
                        style = TextStyle(
                            fontFamily = FontFamily.Serif, fontSize = 15.sp,
                            fontWeight = FontWeight.Light, color = Color.DarkGray
                        )
                    )
                }, icon = {
                    NetworkImageComponentGlide(
                        url = album.images[0].url,
                        modifier = Modifier
                            .width(60.dp)
                            .height(60.dp)
                    )
                })
            }
        })

    }
}

@Preview(showBackground = true, widthDp = 420)
@Composable
fun DefaultPreview() {
    MusicTheme {
        MainScreen(rememberNavController())
    }
}