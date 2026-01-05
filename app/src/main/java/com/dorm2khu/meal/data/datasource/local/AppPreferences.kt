package com.dorm2khu.meal.data.datasource.local

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private const val DATASTORE_NAME = "app_prefs"

private val Context.dataStore by preferencesDataStore(name = DATASTORE_NAME)

class AppPreferences(
    private val appContext: Context
) {
    private val ds = appContext.dataStore

    val fcmTokenFlow: Flow<String?> =
        ds.data.map { it[PreferencesKeys.FCM_TOKEN] }

    val userIdFlow: Flow<String?> =
        ds.data.map { it[PreferencesKeys.USER_ID] }

    val selectedRestaurantIdFlow: Flow<String?> =
        ds.data.map { it[PreferencesKeys.SELECTED_RESTAURANT_ID] }

    suspend fun setFcmToken(token: String) {
        ds.edit { prefs -> prefs[PreferencesKeys.FCM_TOKEN] = token }
    }

    suspend fun setUserId(userId: String) {
        ds.edit { prefs -> prefs[PreferencesKeys.USER_ID] = userId }
    }

    suspend fun setSelectedRestaurantId(restaurantId: String) {
        ds.edit { prefs -> prefs[PreferencesKeys.SELECTED_RESTAURANT_ID] = restaurantId }
    }


    suspend fun clearFcmToken() {
        ds.edit { it.remove(PreferencesKeys.FCM_TOKEN) }
    }

    suspend fun clearAll() {
        ds.edit { it.clear() }
    }

}