package com.dorm2khu.meal.data.repository

sealed interface BootstrapProgress {

    data class Loading(
        val step: Step,
        val message: String
    ) : BootstrapProgress

    object Success : BootstrapProgress

    data class Error(
        val step: Step,
        val message: String,
        val throwable: Throwable? = null,
        val canRetry: Boolean = true
    ) : BootstrapProgress

    enum class Step {
        ENSURE_USER_ID,
        FETCH_FCM_TOKEN,
        SYNC_USER
    }
}
