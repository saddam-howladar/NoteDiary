package com.shcoding.notediary.presentation.screens.home.components

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight

@Composable
fun SignOutAlertDialog(
    title: String,
    message: String,
    isDialogOpened: Boolean,
    onDialogClose: () -> Unit,
    onClickYes: () -> Unit
) {
    if (isDialogOpened) {
        AlertDialog(
            title = {
                Text(
                    text = title,
                    fontSize = MaterialTheme.typography.titleSmall.fontSize,
                    fontWeight = FontWeight.Bold
                )
            },
            text = {
                Text(
                    text = message,
                    fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                    fontWeight = FontWeight.Normal
                )
            },
            confirmButton = {
                Button(onClick = {
                    onClickYes()
                    onDialogClose()
                }) {

                    Text(text = "Yes")

                }
            },
            dismissButton = {
                OutlinedButton(onClick = { onDialogClose() }) {

                    Text(text = "No")

                }
            },
            onDismissRequest = {}
        )
    }

}