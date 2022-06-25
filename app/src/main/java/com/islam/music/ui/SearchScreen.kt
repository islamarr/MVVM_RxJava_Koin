package com.islam.music.ui

import android.util.Log
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.islam.music.R
import com.islam.music.common.EspressoIdlingResource
import com.islam.music.common.NetworkImageComponentGlide
import com.islam.music.features.search.domain.entites.Artist
import com.islam.music.features.search.presentation.viewmodel.SearchActions
import com.islam.music.features.search.presentation.viewmodel.SearchStates
import com.islam.music.features.search.presentation.viewmodel.SearchViewModel
import com.islam.music.ui.theme.MusicTheme


@Composable
fun SearchScreen(navController: NavController) {

    var newValue by remember { mutableStateOf("") }

    val viewModel = hiltViewModel<SearchViewModel>() //TODO you should use state hoisting

    val states by viewModel.state.collectAsState()

    Scaffold(
        topBar = {
            SearchAppBar(
                text = newValue,
                onTextChange = {
                    newValue = it
                },
                onCloseClicked = {
                    newValue = ""
                },
                onSearchClicked = {
                    viewModel.dispatch(SearchActions.SearchArtistByName(it))
                    //   LocalContext.current.setKeyboardVisibility(this, false) //TODO hide keyboard
                }

            )
        },
        content = {
            when (states) {
                is SearchStates.InitialState -> Log.d("TAG", "InitialState")
                is SearchStates.Loading -> LoadingComponent()
                is SearchStates.ArtistListLoaded -> {
                    //  isReachBottom = it.isReachBottom //TODO handle pagination
                    LazyColumnArtistsScrollableComponent(
                        navController,
                        (states as SearchStates.ArtistListLoaded).result.toList()
                    )
                    EspressoIdlingResource.decrement()
                }
                is SearchStates.EmptyArtistList -> {
                    ShowMessage(stringResource(R.string.no_Artists))
                    EspressoIdlingResource.decrement()
                }
                is SearchStates.ShowErrorMessage -> {
                    ShowMessage(stringResource(R.string.error_message))
                    EspressoIdlingResource.decrement()
                }
            }
        },
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
                    text = stringResource(R.string.search_hint),
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
                    onClick = {
                        onSearchClicked(text)
                    }
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
fun LazyColumnArtistsScrollableComponent(navController: NavController, artistList: List<Artist>) {
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
                            fontFamily = FontFamily.Serif, fontSize = 15.sp,
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
                }, modifier = Modifier.clickable {
                    Log.d("TAG", "click: ${artist.name}")
                    navController.navigate("topAlbumsScreen")
                })
            }
        })

    }
}

@Composable
fun ShowMessage(message: String) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = message)
    }
}

@Composable
fun LoadingComponent() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CircularProgressIndicator(modifier = Modifier.wrapContentWidth(Alignment.CenterHorizontally))
    }
}

@Preview(showBackground = true, widthDp = 420)
@Composable
fun DefaultPreview2() {
    MusicTheme {
        SearchScreen(rememberNavController())
    }
}