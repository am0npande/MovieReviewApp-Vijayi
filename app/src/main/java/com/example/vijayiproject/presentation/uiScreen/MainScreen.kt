package com.example.vijayiproject.presentation.uiScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.vijayiproject.navigation.BottomNavGraph
import com.example.vijayiproject.presentation.viewModel.MainViewModel
import com.example.vijayiproject.utils.Screen


@Composable
fun MainScreen(navController: NavHostController,viewModel: MainViewModel){

    val bottomNavController = rememberNavController()

    Scaffold(
        bottomBar = { BottomBar(navController = bottomNavController) }
    ){
        BottomNavGraph(
            bottomNavController = bottomNavController,
            navController = navController,
            viewModel = viewModel,
            innerPadding = it
        )
    }
}

@Composable
fun BottomBar(navController: NavHostController) {

    val items = listOf(
        BottomItem(
            title = Screen.Popular.route,
            icon = Icons.Default.Star
        ),
        BottomItem(
            title = Screen.Upcoming.route,
            icon = Icons.Default.ShoppingCart
        )
    )


    Row(Modifier.background(MaterialTheme.colorScheme.onBackground)) {
        NavigationBar {
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentDestination = navBackStackEntry?.destination?.route

            items.forEach{ screen ->
                NavigationBarItem(
                    selected = currentDestination == screen.title,
                    onClick = {
                        navController.navigate(screen.title) {
                            popUpTo(Screen.Popular.route) { inclusive = false }
                            launchSingleTop = true
                            restoreState = true
                        }
                    },
                    icon = { Icon(screen.icon, contentDescription = null) },
                    label = { Text(screen.title) }
                )
            }
        }
    }
}

data class BottomItem(
    val title: String, val icon: ImageVector
)