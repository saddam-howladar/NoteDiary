package com.shcoding.notediary.presentation.screens.home

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.shcoding.notediary.BuildConfig.APP_ID
import com.shcoding.notediary.presentation.screens.home.components.HomeNavigationDrawer
import com.shcoding.notediary.presentation.screens.home.components.HomeTopAppBar
import com.shcoding.notediary.presentation.screens.home.components.SignOutAlertDialog
import io.realm.kotlin.mongodb.App
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.androidx.compose.BuildConfig
import org.koin.androidx.compose.koinViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(
    viewModel: HomeViewModel = koinViewModel(),
    navigateToWrite: () -> Unit,
    navigateToAuth: () -> Unit
) {

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val coroutineScope = rememberCoroutineScope()
    var isSignOutDialogOpened by remember { mutableStateOf(false) }

    HomeNavigationDrawer(
        drawerState = drawerState,
        onclickSignOut = { isSignOutDialogOpened = true }
    ) {

        Scaffold(topBar = {
            HomeTopAppBar {
                coroutineScope.launch {
                    drawerState.open()
                }
            }
        },
            floatingActionButton = {
                FloatingActionButton(onClick = { navigateToWrite() }) {
                    Icon(imageVector = Icons.Default.Edit, contentDescription = "Edit Icon")
                }
            }) {


        }

        SignOutAlertDialog(
            title = "Sign Out",
            message = "Are you sure? want to Sign out from NoteDiary",
            isDialogOpened = isSignOutDialogOpened,
            onDialogClose = { isSignOutDialogOpened = false },
            onClickYes = {
                coroutineScope.launch(Dispatchers.IO) {
                    val user = App.create(APP_ID).currentUser

                    user?.let {
                        it.logOut()
                        withContext(Dispatchers.Main){
                            navigateToAuth()
                        }

                    }


                }

            })
    }
}