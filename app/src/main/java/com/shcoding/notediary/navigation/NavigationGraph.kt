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
import com.shcoding.notediary.presentation.screens.home.HomeScreen
import com.shcoding.notediary.presentation.screens.write.WriteScreen

@Composable
fun NavigationGraph(startDestination: String, navController: NavHostController) {

    NavHost(navController = navController, startDestination = startDestination) {
        authenticationRoute(navigateToHome = {
            navController.popBackStack()
            navController.navigate(Screen.Home.route)
        })
        homeRoute(navigateToHome = {
            navController.navigate(Screen.Write.route)
        },
        navigateToAuthentication = {
            navController.popBackStack()
            navController.navigate(Screen.Authentication.route)
        })
        writeRoute()
    }

}

fun NavGraphBuilder.authenticationRoute(
    navigateToHome: () -> Unit
) {
    composable(route = Screen.Authentication.route) {
        AuthenticationScreen(
            navigateToHome = navigateToHome
        )
    }
}

fun NavGraphBuilder.homeRoute(
    navigateToHome: () -> Unit,
    navigateToAuthentication: () -> Unit
) {
    composable(route = Screen.Home.route) {

        HomeScreen(
            navigateToWrite = navigateToHome,
            navigateToAuthentication = navigateToAuthentication
        )

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
        WriteScreen()

    }
}