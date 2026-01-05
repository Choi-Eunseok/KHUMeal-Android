package com.dorm2khu.meal.data.model.mapper

import com.dorm2khu.meal.data.model.Restaurant
import com.dorm2khu.meal.data.model.dto.RestaurantDto
import javax.inject.Inject

class RestaurantMapper @Inject constructor() {
    fun toDomain(dto: RestaurantDto): Restaurant =
        Restaurant(
            id = dto.id,
            name = dto.name
        )

    fun toDomainList(dtos: List<RestaurantDto>): List<Restaurant> =
        dtos.map(::toDomain)
}