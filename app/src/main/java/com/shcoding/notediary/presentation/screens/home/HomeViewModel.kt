package com.shcoding.notediary.presentation.screens.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class HomeViewModel: ViewModel() {

    var signOutDialogState by mutableStateOf(false)
    private set

    fun isSignOutDialogOpened(signOutState: Boolean){
        signOutDialogState = signOutState
    }
}