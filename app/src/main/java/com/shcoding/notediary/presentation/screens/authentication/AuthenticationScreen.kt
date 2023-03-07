package com.shcoding.notediary.presentation.screens.authentication

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.shcoding.notediary.R
import com.shcoding.notediary.presentation.components.GoogleButton

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AuthenticationScreen() {

    Scaffold() {
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
                    GoogleButton(loadingState = true) {}
                }

            }

        }
    }

}