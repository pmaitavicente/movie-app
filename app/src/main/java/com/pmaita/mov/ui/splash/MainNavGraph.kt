package com.pmaita.mov.ui.splash

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.pmaita.mov.ui.detail.DetailScreen
import com.pmaita.mov.ui.login.LoginScreen
import com.pmaita.mov.ui.login.LoginViewModel
import com.pmaita.mov.ui.movies.MainScreen
import com.pmaita.mov.ui.movies.MoviesViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun AppNavHost(
    navController: NavHostController,
    startDestination: String = Screen.Splash.route
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(Screen.Splash.route) {
            SplashScreen {
                navController.navigate(Screen.Login.route) {
                    popUpTo(Screen.Splash.route) { inclusive = true }
                }
            }
        }
        composable(Screen.Login.route) {
            val viewModel: LoginViewModel = koinViewModel()
            LoginScreen(viewModel = viewModel) {
                navController.navigate(Screen.Movies.route) {
                    popUpTo(Screen.Login.route) { inclusive = true }
                }
            }
        }
        composable(Screen.Movies.route) {
            val viewModel: MoviesViewModel = koinViewModel()
            MainScreen(viewModel = viewModel) { movie ->
                navController.navigate(Screen.Detail.createRoute(movie.uid))
            }
        }
        composable(
            route = Screen.Detail.route,
            arguments = listOf(navArgument("movieId") { type = NavType.IntType })
        ) { backStackEntry ->
            val movieId = backStackEntry.arguments?.getInt("movieId") ?: return@composable
            val viewModel: MoviesViewModel = koinViewModel()
            DetailScreen(movieId, viewModel) {
                navController.popBackStack()
            }
        }
    }
}