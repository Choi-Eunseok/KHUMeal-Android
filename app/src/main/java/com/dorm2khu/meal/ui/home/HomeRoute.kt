package com.dorm2khu.meal.ui.home

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel

/**
 * iOS HomeViewController에 대응되는 Route
 *
 * 역할:
 * - HomeViewModel 생성/연결
 * - 메뉴 버튼 이벤트 전달
 * - 실제 UI(HomeScreen) 호출
 */
@Composable
fun HomeRoute(
    onMenuButtonTapped: () -> Unit,
    viewModel: HomeViewModel = hiltViewModel()
) {
    HomeScreen(
        state = viewModel.uiState.value,
        onMenuTapped = onMenuButtonTapped,
        onHighlightChanged = viewModel::syncHighlightStatus,
        onRequestScrollToToday = viewModel::scrollToTodayRequested
    )
}