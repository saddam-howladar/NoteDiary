package com.shcoding.notediary.presentation.screens.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.shcoding.notediary.R

@Composable
fun HomeNavigationDrawer(
    drawerState: DrawerState,
    onclickSignOut: () -> Unit,
    content: @Composable () -> Unit
) {

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(height = 250.dp),
                    contentAlignment = Alignment.Center
                ) {

                    Image(
                        modifier = Modifier.size(200.dp).padding(all = 40.dp),
                        painter = painterResource(id = R.drawable.app_logo),
                        contentDescription = "Drawer Logo"
                    )

                }
                NavigationDrawerItem(
                    label = {
                        Row(
                            modifier = Modifier
                                .padding(horizontal = 12.dp)
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.google_icon),
                                contentDescription = "Google Icon",
                                tint = MaterialTheme.colorScheme.onSurface
                            )
                            Spacer(modifier = Modifier.width(width = 12.dp))

                            Text(text = "Sign Out", color = MaterialTheme.colorScheme.onSurface)

                        }
                    },
                    selected = false,
                    onClick = { /*TODO*/onclickSignOut() }
                )
            }
        },
        content = content
    )

}