package com.dorm2khu.meal.util

import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale

data class KoreanDateLabels(val dayLabel: String, val dateLabel: String)

fun String.toKoreanDateLabels(): KoreanDateLabels {
    return runCatching {
        val d = LocalDate.parse(this)
        val dayFormatter = DateTimeFormatter.ofPattern("E요일", Locale.KOREAN)
        val dateFormatter = DateTimeFormatter.ofPattern("MM월 dd일", Locale.KOREAN)
        KoreanDateLabels(
            dayLabel = d.format(dayFormatter),
            dateLabel = d.format(dateFormatter)
        )
    }.getOrElse {
        KoreanDateLabels(dayLabel = "", dateLabel = this)
    }
}