package com.dorm2khu.meal.data.datasource.local

import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey

object PreferencesKeys {
    val FCM_TOKEN = stringPreferencesKey("fcm_token")
    val USER_ID = stringPreferencesKey("user_id")
    val SELECTED_RESTAURANT_ID = intPreferencesKey("selected_restaurant_id")
    val SELECTED_RESTAURANT_NAME = stringPreferencesKey("selected_restaurant_name")
    val IS_IMAGE_MODE = booleanPreferencesKey("is_image_mode")
}