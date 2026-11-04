package com.example.studijetpack.app

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.studijetpack.app.navigation.AppRoute
import com.example.studijetpack.app.navigation.MainScreen
import com.example.studijetpack.app.navigation.logScreenNavigation
import com.example.studijetpack.feature.SplashScreen.SplashScreen
import com.example.studijetpack.feature.login.LoginRoute

@Composable
fun AppNavigation() {

    val navController = rememberNavController()

    DisposableEffect(navController) {
        navController.logScreenNavigation()
        onDispose {}
    }

    NavHost(
        navController = navController,
        startDestination = AppRoute.SPLASH
    ) {

        composable(AppRoute.SPLASH) {
            SplashScreen(
                onNavigateToLogin = {
                    navController.navigate(AppRoute.LOGIN) {
                        popUpTo(AppRoute.SPLASH) {
                            inclusive = true
                        }
                    }
                },
                onNavigateToHome = {
                    navController.navigate(AppRoute.HOME) {
                        popUpTo(AppRoute.SPLASH) {
                            inclusive = true
                        }
                    }
                }
            )
        }

        composable(AppRoute.LOGIN) {
            LoginRoute(
                onNavigateToHome = {
                    navController.navigate(AppRoute.HOME) {
                        popUpTo(AppRoute.LOGIN) {
                            inclusive = true
                        }
                    }
                }
            )
        }

        composable(AppRoute.HOME) {
            MainScreen()
        }
    }
}