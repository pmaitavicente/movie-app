package com.pmaita.mov.ui.movies

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Movie
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.pmaita.mov.domain.model.Movie

@Composable
fun MainScreen(
    viewModel: MoviesViewModel,
    onMovieClick: (Movie.Data) -> Unit
) {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        bottomBar = {
            NavigationBar {
                NavigationBarItem(
                    selected = currentRoute == "movies",
                    onClick = {
                        if (currentRoute != "movies") {
                            navController.navigate("movies") {
                                popUpTo(navController.graph.startDestinationId) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    },
                    icon = { Icon(Icons.Default.Movie, contentDescription = "Películas") },
                    label = { Text("Películas") }
                )
                NavigationBarItem(
                    selected = currentRoute == "favorites",
                    onClick = {
                        if (currentRoute != "favorites") {
                            navController.navigate("favorites") {
                                popUpTo(navController.graph.startDestinationId) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    },
                    icon = { Icon(Icons.Default.Favorite, contentDescription = "Favoritos") },
                    label = { Text("Favoritos") }
                )
            }
        }
    ) { padding ->
        NavHost(
            navController = navController,
            startDestination = "movies",
            modifier = Modifier.padding(padding)
        ) {
            composable("movies") {
                MoviesScreen(
                    viewModel = viewModel,
                    onMovieClick = onMovieClick
                )
            }
            composable("favorites") {
                FavoritesScreen(
                    viewModel = viewModel,
                    onMovieClick = onMovieClick
                )
            }
        }
    }
}