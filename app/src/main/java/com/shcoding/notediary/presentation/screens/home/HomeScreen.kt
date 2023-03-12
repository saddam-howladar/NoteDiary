package com.shcoding.notediary.presentation.screens.home

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.shcoding.notediary.BuildConfig.APP_ID
import com.shcoding.notediary.domain.model.Diary
import com.shcoding.notediary.presentation.screens.home.components.DateHeader
import com.shcoding.notediary.presentation.screens.home.components.HomeNavigationDrawer
import com.shcoding.notediary.presentation.screens.home.components.HomeTopAppBar
import com.shcoding.notediary.presentation.screens.home.components.SignOutAlertDialog
import com.shcoding.notediary.presentation.screens.home.components.diary.DiaryHolder
import io.realm.kotlin.mongodb.App
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.androidx.compose.koinViewModel
import java.time.LocalDate

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(
    viewModel: HomeViewModel = koinViewModel(),
    navigateToWrite: () -> Unit,
    navigateToAuth: () -> Unit
) {

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val coroutineScope = rememberCoroutineScope()

    HomeNavigationDrawer(
        drawerState = drawerState,
        onclickSignOut = { viewModel.isSignOutDialogOpened(signOutState = true) }
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

            DiaryPage()


        }

        SignOutAlertDialog(
            title = "Sign Out",
            message = "Are you sure? want to Sign out from NoteDiary",
            isDialogOpened = viewModel.signOutDialogState,
            onDialogClose = { viewModel.isSignOutDialogOpened(signOutState = false) },
            onClickYes = {
                coroutineScope.launch(Dispatchers.IO) {
                    val user = App.create(APP_ID).currentUser

                    user?.let {
                        it.logOut()
                        withContext(Dispatchers.Main) {
                            navigateToAuth()
                        }

                    }


                }

            })
    }
}


@Composable
fun DiaryPage(
    diaryNotes: Map<LocalDate, List<Diary>> = mapOf(),
    onClick: () -> Unit = {}
) {

    if (diaryNotes.isNotEmpty()) {

        LazyColumn(modifier = Modifier.padding(horizontal = 24.dp)) {
            diaryNotes.forEach { (localDate, diaries) ->

                stickyHeader(key = localDate) {
                    DateHeader(localDate = localDate)
                }
                items(items = diaries, key = { it._id }) {
                    DiaryHolder(diary = it, onClick = { onClick() })
                }

            }
        }

    } else {
        EmptyPage()
    }

}

@Composable
fun EmptyPage(
    title: String = "Empty Diary",
    subTitle: String = "Write something"
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(all = 24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = title,
            fontSize = MaterialTheme.typography.titleMedium.fontSize,
            fontWeight = FontWeight.Medium
        )
        Text(
            text = subTitle,
            fontSize = MaterialTheme.typography.bodyMedium.fontSize,
            fontWeight = FontWeight.Normal
        )

    }

}