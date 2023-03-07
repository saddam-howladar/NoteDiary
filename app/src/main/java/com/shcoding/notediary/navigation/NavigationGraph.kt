package com.shcoding.notediary.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.shcoding.notediary.navigation.util.Constants.WRITE_SCREEN_ARGUMENT_KEY
import com.shcoding.notediary.presentation.screens.authentication.AuthenticationScreen

@Composable
fun NavigationGraph(startDestination: String, navController: NavHostController) {

    NavHost(navController = navController, startDestination = startDestination) {
        authenticationRoute()
        homeRoute()
        writeRoute()
    }

}

fun NavGraphBuilder.authenticationRoute() {
    composable(route = Screen.Authentication.route) {
        AuthenticationScreen()
    }
}

fun NavGraphBuilder.homeRoute() {
    composable(route = Screen.Home.route) {

    }
}

fun NavGraphBuilder.writeRoute() {
    composable(
        route = Screen.Write.route,
        arguments = listOf(navArgument(name = WRITE_SCREEN_ARGUMENT_KEY) {
            type = NavType.StringType
            nullable = true
            defaultValue = null
        })
    ) {

    }
}