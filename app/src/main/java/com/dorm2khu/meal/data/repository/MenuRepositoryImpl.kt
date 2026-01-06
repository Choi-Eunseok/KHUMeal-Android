package com.dorm2khu.meal.data.repository

import com.dorm2khu.meal.data.datasource.remote.MenuRemoteDataSource
import com.dorm2khu.meal.data.model.DailyMealInfo
import com.dorm2khu.meal.data.model.mapper.MenuMapper
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MenuRepositoryImpl @Inject constructor(
    private val remote: MenuRemoteDataSource,
    private val mapper: MenuMapper
) : MenuRepository {

    override suspend fun getThisWeekMenu(
        restaurantId: Int
    ): List<DailyMealInfo> {
        return mapper.toDomainList(
            remote.getThisWeekMenu(restaurantId)
        )
    }
}