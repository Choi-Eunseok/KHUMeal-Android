package com.dorm2khu.meal.ui.settings

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController

@Composable
fun SettingsRoute(
    navController: NavController,
    viewModel: SettingsViewModel = hiltViewModel()
) {
    val isImageMode by viewModel.isImageMode.collectAsState()

    SettingsScreen(
        isImageMode = isImageMode,
        onBack = { navController.popBackStack() },
        onImageModeChange = viewModel::setImageMode
    )
}