package com.dorm2khu.meal.data.model.mapper

import com.dorm2khu.meal.data.model.DailyMealInfo
import com.dorm2khu.meal.data.model.MenuInfo
import com.dorm2khu.meal.data.model.MenuItem
import com.dorm2khu.meal.data.model.dto.DailyMealInfoDto
import com.dorm2khu.meal.data.model.dto.MenuInfoDto
import com.dorm2khu.meal.data.model.dto.MenuItemDto
import javax.inject.Inject

class MenuMapper @Inject constructor() {

    fun toDomain(dto: DailyMealInfoDto): DailyMealInfo =
        DailyMealInfo(
            restaurantName = dto.restaurantName,
            date = dto.date,
            menuInfos = dto.menuInfos.map(::toDomain)
        )

    private fun toDomain(dto: MenuInfoDto): MenuInfo =
        MenuInfo(
            uuid = dto.menuInfoUuid,
            cornerName = dto.cornerName,
            items = dto.items.map(::toDomain),
            imageUrl = dto.imageUrl
        )

    private fun toDomain(dto: MenuItemDto): MenuItem =
        MenuItem(
            uuid = dto.menuItemUuid,
            name = dto.menuItemName
        )

    fun toDomainList(dtos: List<DailyMealInfoDto>): List<DailyMealInfo> =
        dtos.map(::toDomain)
}