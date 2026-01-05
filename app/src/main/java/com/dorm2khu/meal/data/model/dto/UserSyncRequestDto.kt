package com.dorm2khu.meal.data.model.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserSyncRequestDto(
    @SerialName("user_id")
    val userId: String,

    @SerialName("fcm_token")
    val fcmToken: String,

    @SerialName("platform")
    val platform: String
)