package com.github.popular.ui.main.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.github.popular.ui.Screen
import com.github.popular.ui.main.list.RepositoryListScreen
import com.github.popular.ui.main.search.SearchScreen
import org.koin.androidx.compose.getViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun BuildNavigationGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.Search.route
    ) {
        composable(route = Screen.Search.route) {
            SearchScreen(navController = navController, getViewModel())
        }
        composable(route = Screen.RepositoryList.route + "/{search}") {
            it.arguments?.getString("search")?.let { searchTerm ->
                RepositoryListScreen(
                    navController = navController,
                    listViewModel = getViewModel { parametersOf(searchTerm) })
            }
        }
        composable(route = Screen.RepositoryDetail.route) {

        }
    }
}