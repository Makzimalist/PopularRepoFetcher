package com.github.popular.ui.main.list

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.github.popular.ui.components.RepositoryListItem

@Composable
fun RepositoryListScreen(
    navController: NavController,
    listViewModel: ListViewModel,
) {

    val viewState by listViewModel.viewState.collectAsState()
    val repositories = listOf(1, 2, 4)

    Scaffold(
        scaffoldState = rememberScaffoldState(),
        topBar = { TopAppBar(title = { Text(text = "Search") }) },
    ) { contentPadding ->

        when (val data = viewState) {
            is ViewState.Loading -> Loading()
            is ViewState.Data -> {
                LazyColumn(contentPadding = PaddingValues(16.dp)) {
                    items(data.repositories) { repo ->
                        RepositoryListItem(repo)
                    }
                }
            }
            is ViewState.Error -> {
                LoadingError(data.message)
            }
        }
    }
}

@Composable
fun Loading() {
    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        CircularProgressIndicator()
    }
}

@Composable
fun LoadingError(
    message: String
) {
    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column {
            Text(text = "Some thing went terribly wrong", style = MaterialTheme.typography.h5)
            Text(text = message)
        }
    }
}
