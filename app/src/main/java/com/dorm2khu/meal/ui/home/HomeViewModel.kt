package com.dorm2khu.meal.ui.home

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dorm2khu.meal.data.datasource.local.AppPreferences
import com.dorm2khu.meal.data.model.MealContentMode
import com.dorm2khu.meal.data.repository.MenuRepository
import com.dorm2khu.meal.data.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val menuRepository: MenuRepository,
    private val userRepository: UserRepository,
    private val appPreferences: AppPreferences
) : ViewModel() {

    var uiState = mutableStateOf(HomeUiState())
        private set

    val mealContentMode: StateFlow<MealContentMode> =
        appPreferences.isImageModeFlow
            .map { isImage ->
                if (isImage) MealContentMode.Image else MealContentMode.Text
            }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = MealContentMode.Text
            )

    fun onRestaurantChanged(
        restaurantId: Int,
        restaurantName: String
    ) {
        loadWeeklyMeals(restaurantId, restaurantName)
    }

    private fun loadWeeklyMeals(restaurantId: Int, restaurantName: String) {
        viewModelScope.launch {
            uiState.value = uiState.value.copy(
                isLoading = true,
                restaurantId = restaurantId,
                restaurantName = restaurantName
            )

            runCatching {
                val meals = menuRepository.getThisWeekMenu(restaurantId)

                val uuids = meals
                    .flatMap { it.menuInfos }
                    .flatMap { it.items }
                    .map { it.uuid }
                    .distinct()

                val userId = appPreferences.userIdFlow.first().orEmpty()
                val highlighted = if (userId.isNotBlank() && uuids.isNotEmpty()) {
                    userRepository.fetchUserHighlights(userId, uuids)
                } else emptySet()

                meals to highlighted
            }.onSuccess { (meals, highlighted) ->
                uiState.value = uiState.value.copy(
                    isLoading = false,
                    weeklyMeals = meals,
                    highlightedUuids = highlighted
                )
            }.onFailure { e ->
                uiState.value = uiState.value.copy(
                    isLoading = false,
                )
            }
        }
    }

    fun syncHighlightStatus(uuid: String, isSelected: Boolean) {
        val newSet = uiState.value.highlightedUuids.toMutableSet()
        if (isSelected) newSet.add(uuid) else newSet.remove(uuid)

        uiState.value = uiState.value.copy(highlightedUuids = newSet)

        viewModelScope.launch {
            val userId = appPreferences.userIdFlow.first().orEmpty()
            if (userId.isBlank()) return@launch

            runCatching {
                userRepository.updateHighlight(
                    userId = userId,
                    menuItemUuid = uuid,
                    isHighlighted = isSelected
                )
            }
        }
    }


    fun scrollToTodayRequested(): Int {
        val list = uiState.value.weeklyMeals
        if (list.isEmpty()) return 0

        val today = LocalDate.now().toString()
        val idx = list.indexOfFirst { it.date == today }
        return if (idx >= 0) idx else 0
    }
}