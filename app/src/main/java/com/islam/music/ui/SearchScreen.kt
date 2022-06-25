package com.islam.music.ui

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.islam.music.common.NetworkImageComponentGlide
import com.islam.music.features.search.domain.entites.Artist
import com.islam.music.features.search.domain.entites.Image
import com.islam.music.ui.theme.MusicTheme

val artistList = listOf(
    Artist(listOf(Image("")), name = "artist_1"),
    Artist(listOf(Image("")), name = "artist_2"),
    Artist(listOf(Image("")), name = "artist_3")
)

@Composable
fun SearchScreen(navController: NavController) {
    var newValue by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            SearchAppBar(
                // searchWidgetState = searchWidgetState,
                text = newValue,
                onTextChange = {
                    newValue = it
                },
                onCloseClicked = {
                    newValue = ""
                },
                onSearchClicked = {
                    Log.d("Searched Text", it)
                }

            )
        },
        content = { LazyColumnArtistsScrollableComponent(artistList) },
    )

}

@Composable
fun SearchAppBar(
    text: String,
    onTextChange: (String) -> Unit,
    onCloseClicked: () -> Unit,
    onSearchClicked: (String) -> Unit,
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp),
        elevation = AppBarDefaults.TopAppBarElevation,
        color = MaterialTheme.colors.primary
    ) {
        TextField(
            modifier = Modifier
                .fillMaxWidth(),
            value = text,
            onValueChange = {
                onTextChange(it)
            },
            placeholder = {
                Text(
                    modifier = Modifier
                        .alpha(ContentAlpha.medium),
                    text = "Search for artist ...",
                    color = Color.White
                )
            },
            textStyle = TextStyle(
                fontSize = MaterialTheme.typography.subtitle1.fontSize
            ),
            singleLine = true,
            leadingIcon = {
                IconButton(
                    modifier = Modifier
                        .alpha(ContentAlpha.medium),
                    onClick = {}
                ) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Search Icon",
                        tint = Color.White
                    )
                }
            },
            trailingIcon = {
                IconButton(
                    onClick = {
                        if (text.isNotEmpty()) {
                            onTextChange("")
                        } else {
                            onCloseClicked()
                        }
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "Close Icon",
                        tint = Color.White
                    )
                }
            },
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Search
            ),
            keyboardActions = KeyboardActions(
                onSearch = {
                    onSearchClicked(text)
                }
            ),
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.Transparent,
                cursorColor = Color.White.copy(alpha = ContentAlpha.medium)
            ))
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun LazyColumnArtistsScrollableComponent(artistList: List<Artist>) {
    LazyColumn(modifier = Modifier.fillMaxHeight()) {
        items(items = artistList, itemContent = { artist ->
            Card(
                shape = RoundedCornerShape(4.dp),
                backgroundColor = Color.White,
                modifier = Modifier
                    .fillParentMaxWidth()
                    .padding(8.dp)
            ) {
                ListItem(text = {
                    Text(
                        text = artist.name ?: "",
                        style = TextStyle(
                            fontFamily = FontFamily.Serif, fontSize = 25.sp,
                            fontWeight = FontWeight.Bold
                        )
                    )
                }, icon = {
                    NetworkImageComponentGlide(
                        url = artist.images?.get(0)?.url ?: "",
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
fun DefaultPreview2() {
    MusicTheme {
        SearchScreen(rememberNavController())
    }
}