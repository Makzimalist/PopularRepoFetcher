package com.github.popular.ui

import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost

/**
 * Definition of all screens in the App
 *
 * @param route The route string which is used by [NavHostController] and [NavHost]
 */
sealed class Screen(open val route: String) {

    object Search : Screen("search")
    object RepositoryList: Screen("repositoryList")
    object RepositoryDetail: Screen("repositoryDetail")
}
