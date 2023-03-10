package com.shcoding.notediary.presentation.screens.auth

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.shcoding.notediary.BuildConfig
import com.shcoding.notediary.R
import com.shcoding.notediary.presentation.screens.auth.components.GoogleButton
import com.stevdzasan.messagebar.ContentWithMessageBar
import com.stevdzasan.messagebar.rememberMessageBarState
import com.stevdzasan.onetap.OneTapSignInWithGoogle
import com.stevdzasan.onetap.rememberOneTapSignInState
import org.koin.androidx.compose.koinViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AuthScreen(
    viewModel: AuthViewModel = koinViewModel(),
    navigateToHome: () -> Unit
) {
    val oneTapSignInState = rememberOneTapSignInState()
    val loadingState = viewModel.state.value.loadingState
    val authenticated = viewModel.authenticated
    val messageBarState = rememberMessageBarState()
    Scaffold(
        modifier = Modifier
            .background(color = MaterialTheme.colorScheme.surface)
            .statusBarsPadding()
            .navigationBarsPadding()
    ) {

        ContentWithMessageBar(messageBarState = messageBarState, errorMaxLines = 10) {

            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(all = 40.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Column(
                        modifier = Modifier
                            .weight(weight = 9f),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        Image(
                            modifier = Modifier.size(size = 120.dp),
                            painter = painterResource(id = R.drawable.app_logo),
                            contentDescription = "Google Logo♥",
                            contentScale = ContentScale.Crop
                        )
                        Spacer(modifier = Modifier.height(height = 20.dp))

                        Text(
                            text = "Welcome to NoteDiary",
                            fontSize = MaterialTheme.typography.titleLarge.fontSize
                        )
                        Text(
                            text = "Please sign in to continue...",
                            fontSize = MaterialTheme.typography.titleMedium.fontSize,
                            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.50f)
                        )

                    }
                    Column(
                        modifier = Modifier.weight(weight = 1f),
                        verticalArrangement = Arrangement.Bottom
                    ) {
                        GoogleButton(loadingState = loadingState) {
                            oneTapSignInState.open()
                            viewModel.onEvent(event = AuthEvent.setLoading(true))
                        }
                    }

                }

            }
        }
    }
    OneTapSignInWithGoogle(
        state = oneTapSignInState,
        clientId = BuildConfig.CLIENT_ID,
        onTokenIdReceived = {tokenId ->
            viewModel.onEvent(AuthEvent.signInWithMongoDbAtlas(
                tokenId = tokenId,
                onSuccess = {
                    messageBarState.addSuccess(message = "Sign in successfully.")
                    viewModel.onEvent(AuthEvent.setLoading(false))
                },
                onError = { e ->
                    messageBarState.addError(Exception(e))
                }
            )
            )
        },
        onDialogDismissed = {

            messageBarState.addError(Exception(it))
            viewModel.onEvent(AuthEvent.setLoading(false))

        })


    LaunchedEffect(key1 = authenticated) {
        if (authenticated) {
            navigateToHome()
        }
    }



}





