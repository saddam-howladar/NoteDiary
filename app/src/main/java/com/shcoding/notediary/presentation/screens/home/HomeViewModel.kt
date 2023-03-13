package com.shcoding.notediary.presentation.screens.home

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shcoding.notediary.common.utils.RequestState
import com.shcoding.notediary.data.repository.Diaries
import com.shcoding.notediary.data.repository.MongoDBRepository
import com.shcoding.notediary.data.repository.MongoDBRepositoryImpl
import kotlinx.coroutines.launch

class HomeViewModel: ViewModel() {

    var signOutDialogState by mutableStateOf(false)
    private set

    var diaries: MutableState<Diaries> = mutableStateOf(RequestState.Idle)

    init {
        observeAllDiaries()
    }

    fun isSignOutDialogOpened(signOutState: Boolean){
        signOutDialogState = signOutState
    }

    private fun observeAllDiaries(){
       viewModelScope.launch {
           MongoDBRepositoryImpl.getAllDiaries().collect{ diariesResult ->
               diaries.value = diariesResult
           }
       }
    }
}
