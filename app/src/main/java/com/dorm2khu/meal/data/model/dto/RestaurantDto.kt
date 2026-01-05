package com.dorm2khu.meal.data.model.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RestaurantDto(
    @SerialName("id")
    val id: Int,

    @SerialName("name")
    val name: String
)