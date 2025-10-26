package com.example.vijayiproject.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.vijayiproject.presentation.uiScreen.DetailScreen
import com.example.vijayiproject.presentation.uiScreen.MainScreen
import com.example.vijayiproject.presentation.viewModel.MainViewModel
import com.example.vijayiproject.utils.Screen


@Composable
fun NavGraph(
    navController: NavHostController, viewModel: MainViewModel
) {
    NavHost(
        navController = navController, startDestination = Screen.MainScreen.route
    ) {

        composable(Screen.MainScreen.route) {

            MainScreen(
                navController = navController,
                viewModel = viewModel,
            )
        }

        composable(route = Screen.Details.route) {
            DetailScreen(
                navController = navController,
                viewModel = viewModel,
            )
        }
    }
}

