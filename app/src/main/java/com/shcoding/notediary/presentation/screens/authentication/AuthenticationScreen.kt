package com.shcoding.notediary.presentation.screens.authentication

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.Identity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.shcoding.notediary.BuildConfig
import com.shcoding.notediary.R
import com.shcoding.notediary.presentation.screens.authentication.components.GoogleButton
import com.shcoding.notediary.ui.theme.DevicePreviews
import com.stevdzasan.messagebar.ContentWithMessageBar
import com.stevdzasan.messagebar.MessageBarState
import com.stevdzasan.messagebar.rememberMessageBarState
import com.stevdzasan.onetap.OneTapSignInWithGoogle
import com.stevdzasan.onetap.rememberOneTapSignInState
import io.realm.kotlin.internal.platform.runBlocking
import io.realm.kotlin.mongodb.App
import io.realm.kotlin.mongodb.Credentials
import io.realm.kotlin.mongodb.GoogleAuthType
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import org.koin.androidx.compose.koinViewModel
import java.io.Serializable

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AuthenticationScreen(
    viewModel: AuthenticationViewModel = koinViewModel(),
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
                        //.weight(weight = 9f)
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
                            viewModel.onEvent(event = AuthenticationEvent.setLoading(true))
                        }
                    }

                }

            }
        }
    }
    LaunchedEffect(key1 = authenticated) {
        if (authenticated) {
            navigateToHome()
        }
    }

    OneTapSignInWithGoogle(
        state = oneTapSignInState,
        clientId = BuildConfig.CLIENT_ID,
        onTokenIdReceived = {tokenId ->
            viewModel.onEvent(AuthenticationEvent.signInWithMongoDbAtlas(
                tokenId = tokenId,
                onSuccess = {
                    messageBarState.addSuccess(message = "Sign in successfully.")
                    viewModel.onEvent(AuthenticationEvent.setLoading(false))
                },
                onError = { e ->
                    messageBarState.addError(Exception(e))
                }
            )
            )
        },
        onDialogDismissed = {

            messageBarState.addError(Exception(it))
            viewModel.onEvent(AuthenticationEvent.setLoading(false))

        })


}





