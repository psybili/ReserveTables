package com.quandoo.reservetables.data.model

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.quandoo.reservetables.data.model.dao.CustomerDao
import com.quandoo.reservetables.data.model.dao.ReservationDao
import com.quandoo.reservetables.data.model.dao.TableDao

@Database(entities = [(Customer::class), (Table::class), (Reservation::class)], version = 1, exportSchema = false)
abstract class ReservationDatabase : RoomDatabase() {
    abstract fun customerDao(): CustomerDao
    abstract fun tableDao(): TableDao
    abstract fun reservationDao(): ReservationDao
}
