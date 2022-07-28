package com.github.popular.ui.main.search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.github.popular.ui.Screen

@Composable
fun SearchScreen(
    navController: NavController,
    searchViewModel: SearchViewModel
) {

    Scaffold(
        scaffoldState = rememberScaffoldState(),
        topBar = { TopAppBar(title = { Text(text = "Search") }) },
    ) { contentPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(contentPadding),
        ) {
            val text by searchViewModel.searchString.collectAsState()
            OutlinedTextField(
                value = text,
                onValueChange = searchViewModel::search,
                label = { Text("Search") },
                placeholder = { Text(text = "Search for Repository ...") })

            Button(
                onClick = { navController.navigate("${Screen.RepositoryList.route}/$text") },
            ) {
                Text("Search")
            }
        }
    }
}