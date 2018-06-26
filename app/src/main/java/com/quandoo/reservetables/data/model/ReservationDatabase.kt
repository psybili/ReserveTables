package com.quandoo.reservetables.data.model

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.quandoo.reservetables.data.model.dao.CustomerDao

@Database(entities = [(Customer::class)], version = 1, exportSchema = false)
abstract class ReservationDatabase : RoomDatabase() {
    abstract fun customerDao(): CustomerDao
}
