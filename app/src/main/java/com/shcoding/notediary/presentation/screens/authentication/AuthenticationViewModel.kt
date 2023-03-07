package com.shcoding.notediary.presentation.screens.authentication

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shcoding.notediary.BuildConfig
import io.realm.kotlin.mongodb.App
import io.realm.kotlin.mongodb.Credentials
import io.realm.kotlin.mongodb.GoogleAuthType
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AuthenticationViewModel : ViewModel() {

    private val _state = mutableStateOf(AuthenticationState())
    val state: State<AuthenticationState> = _state

    fun onEvent(event: AuthenticationEvent) {

        when (event) {
            is AuthenticationEvent.setLoading -> {
                _state.value.loadingState = event.loading
            }
            is AuthenticationEvent.signInWithMongoDbAtlas -> {
                viewModelScope.launch {
                    try {
                        val result = withContext(Dispatchers.IO) {
                            App.Companion.create(BuildConfig.APP_ID).login(
                                credentials = Credentials.google(
                                    event.tokenId,
                                    GoogleAuthType.ID_TOKEN
                                )
                            ).loggedIn
                        }
                        withContext(Dispatchers.Main) {
                            event.onSuccess(result)
                        }
                    } catch (e: Exception) {
                        withContext(Dispatchers.Main) {
                            event.onError(e)
                        }
                    }
                }
            }
        }
    }
}