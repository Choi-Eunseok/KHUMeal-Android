package com.dorm2khu.meal.data.datasource.local

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.dorm2khu.meal.data.model.Restaurant
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

    val selectedRestaurantFlow = ds.data.map { prefs ->
        val id = prefs[PreferencesKeys.SELECTED_RESTAURANT_ID]
        val name = prefs[PreferencesKeys.SELECTED_RESTAURANT_NAME]

        if (id != null && name != null) {
            Restaurant(id, name)
        } else null
    }

    val isImageModeFlow: Flow<Boolean> =
        ds.data.map { prefs ->
            prefs[PreferencesKeys.IS_IMAGE_MODE] ?: false
        }


    suspend fun setFcmToken(token: String) {
        ds.edit { prefs -> prefs[PreferencesKeys.FCM_TOKEN] = token }
    }

    suspend fun setUserId(userId: String) {
        ds.edit { prefs -> prefs[PreferencesKeys.USER_ID] = userId }
    }

    suspend fun setSelectedRestaurant(restaurant: Restaurant) {
        ds.edit { prefs ->
            prefs[PreferencesKeys.SELECTED_RESTAURANT_ID] = restaurant.id
            prefs[PreferencesKeys.SELECTED_RESTAURANT_NAME] = restaurant.name
        }
    }

    suspend fun setImageMode(isImage: Boolean) {
        ds.edit { prefs ->
            prefs[PreferencesKeys.IS_IMAGE_MODE] = isImage
        }
    }


    suspend fun clearFcmToken() {
        ds.edit { it.remove(PreferencesKeys.FCM_TOKEN) }
    }

    suspend fun clearAll() {
        ds.edit { it.clear() }
    }

}