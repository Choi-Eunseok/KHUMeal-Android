package com.dorm2khu.meal.di

import com.dorm2khu.meal.data.repository.MenuRepository
import com.dorm2khu.meal.data.repository.MenuRepositoryImpl
import com.dorm2khu.meal.data.repository.RestaurantRepository
import com.dorm2khu.meal.data.repository.RestaurantRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindRestaurantRepository(
        impl: RestaurantRepositoryImpl
    ): RestaurantRepository

    @Binds
    @Singleton
    abstract fun bindMenuRepository(
        impl: MenuRepositoryImpl
    ): MenuRepository

    @Binds
    @Singleton
    abstract fun bindUserRepository(
        impl: com.dorm2khu.meal.data.repository.UserRepositoryImpl
    ): com.dorm2khu.meal.data.repository.UserRepository
}