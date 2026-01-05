package com.dorm2khu.meal.ui.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dorm2khu.meal.data.datasource.local.AppPreferences
import com.dorm2khu.meal.data.datasource.remote.UserRemoteDataSource
import com.dorm2khu.meal.data.model.dto.UserSyncRequestDto
import com.google.firebase.messaging.FirebaseMessaging
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.suspendCancellableCoroutine
import java.util.UUID
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val prefs: AppPreferences,
    private val userRemote: UserRemoteDataSource
) : ViewModel() {

    private val _navEvents = MutableSharedFlow<SplashNavEvent>(extraBufferCapacity = 1)
    val navEvents = _navEvents.asSharedFlow()

    fun startBootstrap() {
        viewModelScope.launch {
            val start = System.currentTimeMillis()

            val deviceId = ensureDeviceId()
            val fcmToken = ensureFcmToken()

            runCatching {
                userRemote.syncUser(
                    UserSyncRequestDto(
                        userId = deviceId,
                        fcmToken = fcmToken,
                        platform = "android"
                    )
                )
            }

            // iOS: minimumDisplayTime = 1.5
            val elapsed = System.currentTimeMillis() - start
            val minimum = 1500L
            if (elapsed < minimum) delay(minimum - elapsed)

            _navEvents.tryEmit(SplashNavEvent.ToHome)
        }
    }

    private suspend fun ensureDeviceId(): String {
        val existing = prefs.userIdFlow.first()
        if (!existing.isNullOrBlank()) return existing

        val newId = UUID.randomUUID().toString()
        prefs.setUserId(newId)
        return newId
    }

    private suspend fun ensureFcmToken(): String {
        val existing = prefs.fcmTokenFlow.first()
        if (!existing.isNullOrBlank()) return existing

        val token = fetchFcmToken()
        prefs.setFcmToken(token)
        return token
    }

    private suspend fun fetchFcmToken(): String =
        suspendCancellableCoroutine { cont ->
            FirebaseMessaging.getInstance().token
                .addOnSuccessListener { token -> if (cont.isActive) cont.resume(token) }
                .addOnFailureListener { e -> if (cont.isActive) cont.resumeWithException(e) }
        }
}

sealed interface SplashNavEvent {
    data object ToHome : SplashNavEvent
}