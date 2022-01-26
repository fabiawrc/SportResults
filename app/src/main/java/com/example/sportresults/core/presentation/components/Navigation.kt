package com.example.sportresults.core.presentation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.sportresults.core.util.Screen
import com.example.sportresults.feature_splash.presentation.SplashScreen
import com.example.sportresults.feature_sport_activity.presentation.sport_activity_detail.SportActivityDetailScreen
import com.example.sportresults.feature_sport_activity.presentation.sport_activity_main.SportActivityMainScreen

@Composable
fun Navigation(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = Screen.SportActivityMainScreen.route,
        modifier = Modifier.fillMaxSize()
    ) {
        composable(Screen.SplashScreen.route) {
            SplashScreen(
                onPopBackStack = navController::popBackStack,
                onNavigate = navController::navigate,
                navController = navController
            )
        }
        composable(Screen.SportActivityMainScreen.route){
            SportActivityMainScreen(
                onPopBackStack = navController::popBackStack,
                onNavigate = navController::navigate,
                navController = navController
            )
        }
        composable(Screen.SportActivityDetailScreen.route){
            SportActivityDetailScreen(
                navController = navController,
                onPopBackStack = navController::popBackStack,
                onNavigate = navController::navigate
            )
        }
    }
}