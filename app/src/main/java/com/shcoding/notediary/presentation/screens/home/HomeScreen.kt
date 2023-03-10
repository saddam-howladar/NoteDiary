package com.shcoding.notediary.presentation.screens.home

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.shcoding.notediary.presentation.screens.home.components.HomeTopAppBar
import io.realm.kotlin.mongodb.App
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(
    viewModel: HomeViewModel = koinViewModel(),
    navigateToWrite: () -> Unit,
    navigateToAuth: () -> Unit
) {

    Scaffold(topBar = {
        HomeTopAppBar {

        }
    },
        floatingActionButton = {
            FloatingActionButton(onClick = { navigateToWrite() }) {
                Icon(imageVector = Icons.Default.Edit, contentDescription = "Edit Icon")
            }
        }) {

        val scope = rememberCoroutineScope()

        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            IconButton(onClick = {
                scope.launch {
                    App.Companion.create(com.shcoding.notediary.BuildConfig.APP_ID).currentUser?.logOut()
                    navigateToAuth()
                }
            }) {
                Icon(imageVector = Icons.Default.Logout, contentDescription = "Logout Icon")
            }

        }
    }
}