package com.quandoo.reservetables.data.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.Ignore
import android.arch.persistence.room.PrimaryKey
import android.databinding.BaseObservable
import android.databinding.Bindable

@Entity
data class Table(
        @PrimaryKey
        @Bindable
        val id: Long,
        @Bindable
        var available: Boolean
) : BaseObservable() {

    @Ignore
    @Bindable
    var label: String = "T: $id"

}