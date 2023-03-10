package com.shcoding.notediary.presentation.screens.auth.components

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.shcoding.notediary.R
import com.shcoding.notediary.ui.theme.DevicePreviews


@Composable
fun GoogleButton(
    modifier: Modifier = Modifier,
    loadingState: Boolean = false,
    primaryText: String = "Sign in with Google",
    secondaryText: String = "Please wait...",
    buttonIcon: Int = R.drawable.google_icon,
    shape: Shape = Shapes().extraSmall,
    borderStrokWidth: Dp = 1.dp,
    borderColor: Color = MaterialTheme.colorScheme.surfaceVariant,
    backgroundColor: Color = MaterialTheme.colorScheme.surface,
    progressIndicatorColor: Color = MaterialTheme.colorScheme.primary,
    onClick: () -> Unit

) {
    var buttonText by remember {
        mutableStateOf(primaryText)
    }

    LaunchedEffect(key1 = loadingState) {
        buttonText = if (loadingState) secondaryText else primaryText
    }

    Surface(
        modifier = modifier.clickable(enabled = !loadingState) { onClick() },
        shape = shape,
        border = BorderStroke(width = borderStrokWidth, color = borderColor),
        color = backgroundColor
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(all = 12.dp)
                .animateContentSize(
                    animationSpec = tween(
                        durationMillis = 300,
                        easing = LinearOutSlowInEasing
                    )
                ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {

            Icon(
                painter = painterResource(id = buttonIcon),
                contentDescription = "Google Icon",
                tint = Color.Unspecified
            )

            Spacer(modifier = Modifier.width(width = 8.dp))

            Text(
                text = buttonText,
               fontSize = MaterialTheme.typography.bodyMedium.fontSize
            )

            if (loadingState) {
                Spacer(modifier = Modifier.width(width = 16.dp))
                CircularProgressIndicator(
                    modifier = Modifier.size(size = 16.dp),
                    strokeWidth = 2.dp,
                    color = progressIndicatorColor
                )
            }

        }

    }
}

@DevicePreviews
@Composable
fun GoogleButtonPreview() {
    GoogleButton {}
}

@DevicePreviews
@Composable
fun GoogleButtonInteractivePreview() {
    GoogleButton(loadingState = true) {}
}