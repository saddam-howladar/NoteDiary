package com.shcoding.notediary

import android.os.Bundle
import android.window.SplashScreen
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import androidx.navigation.compose.rememberNavController
import com.shcoding.notediary.navigation.NavigationGraph
import com.shcoding.notediary.navigation.Screen
import com.shcoding.notediary.ui.theme.NoteDiaryTheme
import io.realm.kotlin.mongodb.App

class NoteDiaryActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            NoteDiaryTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavigationGraph(
                        startDestination = getStartDestination(),
                        navController = navController
                    )
                }
            }
        }
    }
}

private fun getStartDestination(): String {
    val user = App.Companion.create(BuildConfig.APP_ID).currentUser
    return if (user != null && user.loggedIn) Screen.Home.route else Screen.Auth.route
}