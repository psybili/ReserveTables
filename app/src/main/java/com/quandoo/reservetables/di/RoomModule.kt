package com.quandoo.reservetables.di

import android.arch.persistence.room.Room
import com.quandoo.reservetables.App
import com.quandoo.reservetables.data.api.CustomerService
import com.quandoo.reservetables.data.api.TableService
import com.quandoo.reservetables.data.model.ReservationDatabase
import com.quandoo.reservetables.data.model.dao.CustomerDao
import com.quandoo.reservetables.data.model.dao.ReservationDao
import com.quandoo.reservetables.data.model.dao.TableDao
import com.quandoo.reservetables.data.repository.CustomerRepository
import com.quandoo.reservetables.data.repository.ReservationRepository
import com.quandoo.reservetables.data.repository.TableRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [DataModule::class])
class RoomModule(app: App) {

    private val database: ReservationDatabase = Room.databaseBuilder(
            app,
            ReservationDatabase::class.java,
            "Reservation.db"
    ).build()

    @Provides
    @Singleton
    fun provideCustomerRepository(
            customerDao: CustomerDao,
            customerService: CustomerService
    ): CustomerRepository {
        return CustomerRepository(customerService, customerDao)
    }

    @Provides
    @Singleton
    fun provideTableRepository(
            tableDao: TableDao,
            tableService: TableService
    ): TableRepository {
        return TableRepository(tableService, tableDao)
    }

    @Provides
    @Singleton
    fun provideReservationRepository(
            reservationDao: ReservationDao,
            customerService: CustomerService
    ): ReservationRepository{
        return ReservationRepository(customerService, reservationDao)
    }

    @Provides
    @Singleton
    fun provideCustomerDao(database: ReservationDatabase): CustomerDao {
        return database.customerDao()
    }

    @Provides
    @Singleton
    fun provideTableDao(database: ReservationDatabase): TableDao {
        return database.tableDao()
    }

    @Provides
    @Singleton
    fun provideReservationDao(database: ReservationDatabase): ReservationDao {
        return database.reservationDao()
    }

    @Provides
    @Singleton
    fun provideCustomerDatabase(): ReservationDatabase {
        return database
    }
}
