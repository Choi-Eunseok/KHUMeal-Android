package com.dorm2khu.meal.ui.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import com.dorm2khu.meal.ui.splash.SplashRoute
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.fillMaxSize
import com.dorm2khu.meal.ui.main.MainContainerRoute

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AppNavGraph() {
    val navController = rememberAnimatedNavController()

    AnimatedNavHost(
        navController = navController,
        startDestination = Routes.SPLASH,
        enterTransition = { fadeIn(tween(500)) },
        exitTransition = { fadeOut(tween(500)) },
        popEnterTransition = { fadeIn(tween(500)) },
        popExitTransition = { fadeOut(tween(500)) }
    ) {
        composable(Routes.SPLASH) { SplashRoute(navController) }
        composable(Routes.MAIN) {
            MainContainerRoute()
        }
    }
}
