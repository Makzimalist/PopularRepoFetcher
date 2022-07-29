package com.github.popular.ui.main.list

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.github.popular.ui.components.RepositoryListItem
import com.github.popular.ui.components.Sorting

@Composable
fun RepositoryListScreen(
    navController: NavController,
    listViewModel: ListViewModel,
) {
    val viewState by listViewModel.viewState.collectAsState()

    Scaffold(
        scaffoldState = rememberScaffoldState(),
        topBar = {
            TopAppBar(title = { Text(text = listViewModel.title.collectAsState().value) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = null,
                        )
                    }
                })
        },
    ) {

        when (val data = viewState) { // to smart cast the data
            is ViewState.Loading -> Loading()
            is ViewState.Data -> {
                LazyColumn(contentPadding = PaddingValues(16.dp)) {
                    items(data.repositories) { repo ->
                        // string mapping is not the best idea, should have passed some kind of sorting object (but time is running out)
                        val sorting = when (listViewModel.sorting.collectAsState().value) {
                            "forks" -> Sorting.FORK
                            else -> Sorting.STAR
                        }
                        RepositoryListItem(repo, sorting = sorting)
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
    Box(
        Modifier
            .fillMaxSize()
            .padding(24.dp), contentAlignment = Alignment.Center) {
        Column {
            Text(text = "Something went terribly wrong", style = MaterialTheme.typography.h5)
            Text(text = "Please enter a valid search string, or check your internet connection.", style = MaterialTheme.typography.body1)
            Text(text = message)
        }
    }
}
