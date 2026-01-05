package com.dorm2khu.meal.data.datasource.local

import androidx.datastore.preferences.core.stringPreferencesKey

object PreferencesKeys {
    val FCM_TOKEN = stringPreferencesKey("fcm_token")
    val USER_ID = stringPreferencesKey("user_id")
    val SELECTED_RESTAURANT_ID = stringPreferencesKey("selected_restaurant_id")
}