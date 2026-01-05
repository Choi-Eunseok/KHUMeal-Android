package com.dorm2khu.meal.ui.sidemenu

import com.dorm2khu.meal.data.model.Restaurant

sealed interface SideMenuNavEvent {
    data object ToSettings : SideMenuNavEvent
    data class RestaurantChanged(val restaurant: Restaurant) : SideMenuNavEvent
}