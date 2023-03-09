package com.shcoding.notediary.presentation.screens.authentication

import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shcoding.notediary.BuildConfig
import io.realm.kotlin.mongodb.App
import io.realm.kotlin.mongodb.Credentials
import io.realm.kotlin.mongodb.GoogleAuthType
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AuthenticationViewModel : ViewModel() {

    var authenticated by mutableStateOf(false)
    private set

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
                            App.create(BuildConfig.APP_ID).login(
                                credentials = Credentials.google(
                                    event.tokenId,
                                    GoogleAuthType.ID_TOKEN
                                )
                            ).loggedIn
                        }
                        withContext(Dispatchers.Main) {
                            if (result){
                                event.onSuccess()
                                delay(600)
                                authenticated = true
                            }else{
                                event.onError(Exception("User not logged in."))
                            }

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