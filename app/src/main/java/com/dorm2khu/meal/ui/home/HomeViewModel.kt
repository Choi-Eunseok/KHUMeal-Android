package com.dorm2khu.meal.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dorm2khu.meal.ui.home.model.MenuUiModel
import com.dorm2khu.meal.ui.home.model.WeeklyMealUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    // TODO: MealRepository 주입 (restaurantId 기준으로 메뉴 로드)
) : ViewModel() {

    var uiState = androidx.compose.runtime.mutableStateOf(HomeUiState())
        private set

    /**
     * SideMenu에서 식당 선택이 변경되었을 때 호출
     * iOS: didSelectRestaurant(_:)
     */
    fun onRestaurantChanged(
        restaurantId: Int,
        restaurantName: String
    ) {
        loadWeeklyMeals(
            restaurantId = restaurantId,
            restaurantName = restaurantName
        )
    }

    /**
     * 식당 기준으로 주간 메뉴 로드
     */
    private fun loadWeeklyMeals(
        restaurantId: Int,
        restaurantName: String
    ) {
        viewModelScope.launch {
            uiState.value = uiState.value.copy(
                isLoading = true,
                restaurantName = restaurantName
            )

            // TODO: 실제 API 연동 (restaurantId 사용)
            delay(600)

            uiState.value = uiState.value.copy(
                isLoading = false,
                weeklyMeals = mockWeeklyMeals(),
                highlightedUuids = emptySet()
            )
        }
    }

    /**
     * iOS: syncHighlightStatus(uuid:status:)
     */
    fun syncHighlightStatus(uuid: String, isSelected: Boolean) {
        val newSet = uiState.value.highlightedUuids.toMutableSet()
        if (isSelected) newSet.add(uuid) else newSet.remove(uuid)

        uiState.value = uiState.value.copy(
            highlightedUuids = newSet
        )

        // TODO: 서버 동기화
    }

    /**
     * iOS: scrollToToday()
     */
    fun scrollToTodayRequested(): Int {
        // TODO: 실제 날짜 계산
        return 0
    }

    // -----------------------
    // Mock data
    // -----------------------

    private fun mockWeeklyMeals(): List<WeeklyMealUiModel> {
        return listOf(
            WeeklyMealUiModel(
                dateLabel = "월 09.23",
                menuInfos = listOf(
                    MenuUiModel("1", "김치찌개"),
                    MenuUiModel("2", "계란말이")
                )
            ),
            WeeklyMealUiModel(
                dateLabel = "화 09.24",
                menuInfos = listOf(
                    MenuUiModel("3", "불고기"),
                    MenuUiModel("4", "된장국")
                )
            ),
            WeeklyMealUiModel(
                dateLabel = "수 09.25",
                menuInfos = listOf(
                    MenuUiModel("5", "제육볶음"),
                    MenuUiModel("6", "미역국")
                )
            )
        )
    }
}