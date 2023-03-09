package com.shcoding.notediary.presentation.screens.home.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.shcoding.notediary.R

@Composable
fun HomeTopAppBar(onClickMenu: () -> Unit) {
    TopAppBar(
        title = {
            Text(text = stringResource(id = R.string.app_name))
        },
        navigationIcon = {
            IconButton(onClick = { onClickMenu() }) {
                Icon(imageVector = Icons.Default.Menu, contentDescription = "Menu Icon")
            }
        },
        actions = {
            IconButton(onClick = { onClickMenu() }) {
                Icon(
                    imageVector = Icons.Default.DateRange,
                    contentDescription = "Date Icon",
                )
            }
        }
    )
}