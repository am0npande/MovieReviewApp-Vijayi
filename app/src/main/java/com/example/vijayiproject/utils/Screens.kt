package com.example.vijayiproject.utils

sealed class Screen(val route: String) {

    object Popular : Screen(ScreenName.POPULAR.route)
    object Upcoming : Screen(ScreenName.UPCOMING.route)
    object Details : Screen(ScreenName.DETAILS.route)
    object MainScreen:Screen(ScreenName.MAINSCREEN.route)
}

enum class ScreenName(val route: String){

    POPULAR("Popular"),
    UPCOMING("Upcoming"),
    DETAILS("Details"),

    MAINSCREEN("Main_Screen")


}