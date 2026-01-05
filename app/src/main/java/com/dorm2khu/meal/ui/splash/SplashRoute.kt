package com.dorm2khu.meal.ui.splash

import androidx.compose.runtime.*
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.dorm2khu.meal.ui.navigation.Routes

@Composable
fun SplashRoute(
    navController: NavController,
    viewModel: SplashViewModel = hiltViewModel()
) {
    var startFadeIn by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        startFadeIn = true
    }

    LaunchedEffect(Unit) {
        viewModel.startBootstrap()
    }

    LaunchedEffect(Unit) {
        viewModel.navEvents.collect { ev ->
            when (ev) {
                SplashNavEvent.ToHome -> {
                    navController.navigate(Routes.HOME) {
                        popUpTo(Routes.SPLASH) { inclusive = true }
                        launchSingleTop = true
                    }
                }
            }
        }
    }

    SplashScreen(startFadeIn = startFadeIn)
}