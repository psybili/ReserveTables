package com.quandoo.reservetables.data.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.databinding.BaseObservable
import android.databinding.Bindable
import android.os.Parcel
import android.os.Parcelable

@Entity
data class Customer(
        @Bindable
        val customerFirstName: String,
        val customerLastName: String,
        @PrimaryKey
        @Bindable
        val id: Long
)  : BaseObservable(), Parcelable {

        var reservationInfo: String? = null

        constructor(parcel: Parcel) : this(
                parcel.readString(),
                parcel.readString(),
                parcel.readLong()) {
                reservationInfo = parcel.readString()
        }

        override fun toString(): String {
                return "$customerLastName, $customerFirstName"
        }

        override fun writeToParcel(parcel: Parcel, flags: Int) {
                parcel.writeString(customerFirstName)
                parcel.writeString(customerLastName)
                parcel.writeLong(id)
                parcel.writeString(reservationInfo)
        }

        override fun describeContents(): Int {
                return 0
        }

        companion object CREATOR : Parcelable.Creator<Customer> {
                override fun createFromParcel(parcel: Parcel): Customer {
                        return Customer(parcel)
                }

                override fun newArray(size: Int): Array<Customer?> {
                        return arrayOfNulls(size)
                }
        }
}