package com.quandoo.reservetables.data.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.databinding.BaseObservable
import android.databinding.Bindable

@Entity
data class Customer(
        @Bindable
        val customerFirstName: String,
        val customerLastName: String,
        @PrimaryKey
        @Bindable
        val id: Long
)  : BaseObservable()