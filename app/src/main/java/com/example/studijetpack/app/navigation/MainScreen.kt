    package com.example.studijetpack.app.navigation

    import androidx.compose.foundation.layout.WindowInsets
    import androidx.compose.foundation.layout.padding
    import androidx.compose.material3.Scaffold
    import androidx.compose.runtime.Composable
    import androidx.compose.runtime.getValue
    import androidx.compose.ui.Modifier
    import androidx.navigation.NavGraph.Companion.findStartDestination
    import androidx.navigation.compose.NavHost
    import androidx.navigation.compose.composable
    import androidx.navigation.compose.currentBackStackEntryAsState
    import androidx.navigation.compose.rememberNavController
    import com.example.studijetpack.core.component.BaseBottomBar
    import com.example.studijetpack.feature.account.AccountScreen
    import com.example.studijetpack.feature.home.HomeScreen
    import com.example.studijetpack.feature.promotion.PromotionScreen
    import com.example.studijetpack.feature.transaction.TransactionScreen

    @Composable
    fun MainScreen() {

        val navController = rememberNavController()

        val navBackStackEntry by
        navController.currentBackStackEntryAsState()

        val currentRoute = navBackStackEntry?.destination?.route

        Scaffold(
            contentWindowInsets = WindowInsets(0, 0, 0, 0),
            bottomBar = {
                BaseBottomBar(
                    currentRoute = currentRoute,
                    onNavigate = { route ->

                        navController.navigate(route) {

                            popUpTo(
                                navController.graph.findStartDestination().id
                            ) {
                                saveState = true
                            }

                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                )
            }
        ) { innerPadding ->

            NavHost(
                navController = navController,
                startDestination = AppRoute.HOME,
                modifier = Modifier.padding(innerPadding)
            ) {

                composable(AppRoute.HOME) {
                    HomeScreen(
                        onServiceClick = {},
                        onPromotionClick = {
                            navController.navigate(AppRoute.PROMOTION)
                        },
                        onCallClick = {}
                    )
                }

                composable(AppRoute.TRANSACTION) {
                    TransactionScreen()
                }

                composable(AppRoute.PROMOTION) {
                    PromotionScreen()
                }

                composable(AppRoute.ACCOUNT) {
                    AccountScreen(
                        onLogout = {
                            navController.navigate(AppRoute.LOGIN) {
                                popUpTo(AppRoute.HOME) {
                                    inclusive = true
                                }
                                launchSingleTop = true
                            }
                        }
                    )
                }
            }
        }
    }