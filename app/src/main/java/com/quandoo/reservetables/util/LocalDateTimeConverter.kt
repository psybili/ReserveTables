package com.quandoo.reservetables.util

import android.arch.persistence.room.TypeConverter
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class LocalDateTimeConverter {
    @TypeConverter
    fun fromString2LocalDateTime(value: String?): LocalDateTime {
        if (value != null) {
            return LocalDateTime.parse(value)
        } else {
            return LocalDateTime.now()
        }
    }

    @TypeConverter
    fun fromLocalDateTime2String(localDateTime: LocalDateTime): String {
        return localDateTime.format(DateTimeFormatter.ISO_DATE_TIME)
    }
}
