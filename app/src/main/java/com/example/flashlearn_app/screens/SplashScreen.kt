package com.example.flashlearn_app.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.flashlearn_app.R
import com.example.flashlearn_app.navigation.Screen
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavHostController) {

    LaunchedEffect(Unit) {
        delay(2000)
        navController.navigate(Screen.Login.route)
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF0B1E3C)),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.flashlearn_logo),
            contentDescription = stringResource(id = R.string.app_logo_description),
            modifier = Modifier.size(260.dp)
        )
    }
}
