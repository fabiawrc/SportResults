package com.example.sportresults.core.util

sealed class Screen(val route: String) {
    object SplashScreen: Screen("splash_screen")
    object SportActivityMainScreen: Screen("activity_main_screen")
    object SportActivityDetailScreen: Screen("activity_detail_screen")
}
