package com.test.coinapi.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.test.coinapi.presentation.details.DetailsScreen
import com.test.coinapi.presentation.main.MainScreen
import com.test.coinapi.presentation.navigation.NavigationItem.Companion.exchangeIdArgument

@Composable
fun AppNavHost() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = NavigationItem.Main.route
    ) {
        composable(NavigationItem.Main.route) {
            MainScreen(navController = navController)
        }
        composable(
            NavigationItem.Details.route,
            arguments = listOf(
                navArgument(exchangeIdArgument){
                    type = NavType.StringType
                }
            )
        ) { backStackEntry ->
            val exchangeId = backStackEntry.arguments?.getString(exchangeIdArgument)
            DetailsScreen(navController = navController, exchangeId)
        }
    }
}