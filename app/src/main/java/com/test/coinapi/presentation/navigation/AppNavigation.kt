package com.test.coinapi.presentation.navigation

enum class AppNavigation {
    MAIN,
    DETAILS
}

sealed class NavigationItem(val route: String) {
    object Main : NavigationItem(AppNavigation.MAIN.name)
    object Details : NavigationItem("${AppNavigation.DETAILS.name}/{$exchangeIdArgument}")
    companion object {
        const val exchangeIdArgument = "exchangeId"
    }
}