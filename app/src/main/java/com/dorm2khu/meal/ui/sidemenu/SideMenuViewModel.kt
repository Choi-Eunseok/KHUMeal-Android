package com.dorm2khu.meal.ui.sidemenu

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dorm2khu.meal.data.datasource.local.AppPreferences
import com.dorm2khu.meal.data.model.Restaurant
import com.dorm2khu.meal.data.repository.RestaurantRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SideMenuViewModel @Inject constructor(
    private val restaurantRepository: RestaurantRepository,
    private val prefs: AppPreferences
) : ViewModel() {

    var restaurants: List<Restaurant> by mutableStateOf(emptyList())
        private set

    var selectedRestaurantId: Int? by mutableStateOf(null)
        private set

    var selectedRestaurantName: String? by mutableStateOf(null)
        private set

    var isLoading: Boolean by mutableStateOf(false)
        private set

    var errorMessage: String? by mutableStateOf(null)
        private set

    val settingsTitle: String = "설정"

    private val _navEvents = MutableSharedFlow<SideMenuNavEvent>(extraBufferCapacity = 1)
    val navEvents: SharedFlow<SideMenuNavEvent> = _navEvents.asSharedFlow()

    fun fetchMenuDataIfNeeded() {
        if (restaurants.isNotEmpty() || isLoading) return
        fetchMenuData()
    }

    fun fetchMenuData() {
        if (isLoading) return

        viewModelScope.launch {
            isLoading = true
            errorMessage = null

            runCatching {
                restaurantRepository.getRestaurants()
            }.onSuccess { list ->
                restaurants = list

                val saved: Restaurant? = runCatching { prefs.selectedRestaurantFlow.first() }.getOrNull()

                val chosen: Restaurant? =
                    restaurants.firstOrNull { it.id == saved?.id }
                        ?: saved
                        ?: restaurants.firstOrNull()

                selectedRestaurantId = chosen?.id
                selectedRestaurantName = chosen?.name

                if (chosen != null) {
                    prefs.setSelectedRestaurant(chosen)
                }

                chosen?.let { id ->
                    _navEvents.tryEmit(SideMenuNavEvent.RestaurantChanged(chosen))
                }
            }.onFailure { e ->
                errorMessage = e.message ?: "식당 목록을 불러오지 못했습니다."
            }

            isLoading = false
        }
    }

    fun selectRestaurant(id: Int) {
        val res = restaurants.firstOrNull { it.id == id } ?: return
        if (selectedRestaurantId == id) return

        selectedRestaurantId = res.id
        selectedRestaurantName = res.name

        viewModelScope.launch {
            prefs.setSelectedRestaurant(res)
        }

        _navEvents.tryEmit(SideMenuNavEvent.RestaurantChanged(res))
    }

    fun openSettings() {
        _navEvents.tryEmit(SideMenuNavEvent.ToSettings)
    }
}