package com.github.popular.ui.main.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.github.popular.ui.Screen
import com.github.popular.ui.main.search.SearchScreen
import org.koin.androidx.compose.getViewModel

@Composable
fun BuildNavigationGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.Search.route
    ) {
        composable(route = Screen.Search.route) {
            SearchScreen(navController = navController, getViewModel())
        }
        composable(route = Screen.RepositoryList.route) {

        }
        composable(route = Screen.RepositoryDetail.route) {

        }
    }
}