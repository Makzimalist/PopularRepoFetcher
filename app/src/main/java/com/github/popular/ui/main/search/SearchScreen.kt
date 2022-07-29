package com.github.popular.ui.main.search

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.github.popular.ui.Screen

@Composable
fun SearchScreen(
    navController: NavController,
    searchViewModel: SearchViewModel
) {
    val text by searchViewModel.searchString.collectAsState()
    val sorting by searchViewModel.sorting.collectAsState()

    val expandState = remember { mutableStateOf(false) }

    // this could be a bit more splitted up into smaller composables - but time ;)
    Scaffold(
        scaffoldState = rememberScaffoldState(),
        topBar = { TopAppBar(title = { Text(text = "Search") }) },
    ) { contentPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(contentPadding),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedTextField(
                modifier = Modifier.padding(top = 32.dp, bottom = 16.dp),
                value = text,
                onValueChange = searchViewModel::search,
                label = { Text("Search") },
                placeholder = { Text(text = "Search for Repositories ...") })

            Button(
                modifier = Modifier.sizeIn(minWidth = 150.dp),
                onClick = { expandState.value = true }) {
                Text(text = sorting)

                DropdownMenu(
                    expanded = expandState.value,
                    onDismissRequest = { expandState.value = false },
                    modifier = Modifier.widthIn(max = 300.dp, min = 100.dp)
                ) {
                    searchViewModel.sortingOptions.forEach {
                        // date is currently not working :(
                        DropdownMenuItem(onClick = {
                            searchViewModel.updateSorting(it)
                            expandState.value = false
                        }) {
                            Text(it)
                        }
                    }
                }
            }

            Button(
                modifier = Modifier.sizeIn(minWidth = 150.dp),
                onClick = {
                    val route = if (text.isEmpty()) {
                        Screen.RepositoryList.route
                    } else {
                        "${Screen.RepositoryList.route}/$text/$sorting"
                    }
                    navController.navigate(route)
                },
            ) {
                Text("Search")
            }
        }
    }
}