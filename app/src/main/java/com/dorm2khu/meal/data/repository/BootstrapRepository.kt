package com.dorm2khu.meal.data.repository

import com.dorm2khu.meal.data.datasource.local.AppPreferences
import com.dorm2khu.meal.data.datasource.remote.UserRemoteDataSource
import com.dorm2khu.meal.data.model.dto.UserSyncRequestDto
import com.google.firebase.messaging.FirebaseMessaging
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.suspendCancellableCoroutine
import java.util.UUID
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

class BootstrapRepository @Inject constructor(
    private val prefs: AppPreferences,
    private val userRemote: UserRemoteDataSource
) {

    fun bootstrap(): Flow<BootstrapProgress> = flow {
        emit(BootstrapProgress.Loading(BootstrapProgress.Step.ENSURE_USER_ID, "사용자 정보 준비 중..."))
        val userId = ensureUserId()

        emit(BootstrapProgress.Loading(BootstrapProgress.Step.FETCH_FCM_TOKEN, "푸시 토큰 확인 중..."))
        val fcmToken = ensureFcmToken()

        emit(BootstrapProgress.Loading(BootstrapProgress.Step.SYNC_USER, "서버와 동기화 중..."))
        try {
            userRemote.syncUser(
                UserSyncRequestDto(
                    userId = userId,
                    fcmToken = fcmToken,
                    platform = "android"
                )
            )
        } catch (t: Throwable) {
            emit(
                BootstrapProgress.Error(
                    step = BootstrapProgress.Step.SYNC_USER,
                    message = "서버 동기화에 실패했어요.",
                    throwable = t,
                    canRetry = true
                )
            )
            return@flow
        }

        emit(BootstrapProgress.Success)
    }.flowOn(Dispatchers.IO)

    private suspend fun ensureUserId(): String {
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