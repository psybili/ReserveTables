package com.quandoo.reservetables.data.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.Ignore
import android.arch.persistence.room.Index
import android.arch.persistence.room.TypeConverters
import android.databinding.BaseObservable
import android.databinding.Bindable
import com.quandoo.reservetables.util.LocalDateTimeConverter
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle

@Entity(
        indices = [
            Index("customerLastName"),
            Index("tableId")],
        primaryKeys = ["customerLastName", "tableId"]
)
data class Reservation(
        @Bindable
        val customerLastName: String,
        @Bindable
        val tableId: Long,
        @Bindable
        @field:TypeConverters(LocalDateTimeConverter::class)
        val expirationDate: LocalDateTime = LocalDateTime.now().plusMinutes(15L)!!
) : BaseObservable() {

    @Ignore
    var label: String = "T$tableId for $customerLastName, expires @ ${expirationDate.format(DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT))}"

}