package com.example.vijayiproject.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.vijayiproject.presentation.uiScreen.PopularScreen
import com.example.vijayiproject.presentation.uiScreen.UpComningScreen
import com.example.vijayiproject.presentation.viewModel.MainViewModel
import com.example.vijayiproject.utils.Screen

@Composable
fun BottomNavGraph(
    navController: NavHostController,
    viewModel: MainViewModel,
    innerPadding: PaddingValues,
    bottomNavController: NavHostController,
) {


    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = innerPadding.calculateBottomPadding())
    ) {
        NavHost(
            navController = bottomNavController, startDestination = Screen.Popular.route
        ) {
            composable(Screen.Popular.route) {

                PopularScreen(
                    navController = navController,
                    uiState = viewModel.uiState.collectAsState().value,
                    viewModel = viewModel,

                    )
            }
            composable(Screen.Upcoming.route) {

                UpComningScreen(

                    navController = navController,
                    uiState = viewModel.uiState.collectAsState().value,
                    viewModel = viewModel,
                )
            }
        }
    }
}