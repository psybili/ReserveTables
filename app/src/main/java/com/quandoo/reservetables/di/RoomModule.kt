package com.quandoo.reservetables.di

import android.app.Application
import android.arch.lifecycle.ViewModelProvider
import android.arch.persistence.room.Room
import com.quandoo.reservetables.App
import com.quandoo.reservetables.data.api.ReservationService
import com.quandoo.reservetables.data.model.ReservationDatabase
import com.quandoo.reservetables.data.model.dao.CustomerDao
import com.quandoo.reservetables.data.repository.CustomerRepository
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
    fun provideCustomerRepository(customerDao: CustomerDao, reservationService: ReservationService): CustomerRepository {
        return CustomerRepository(reservationService, customerDao)
    }

    @Provides
    @Singleton
    fun provideCustomerDao(database: ReservationDatabase): CustomerDao {
        return database.customerDao()
    }

    @Provides
    @Singleton
    fun provideCustomerDatabase(): ReservationDatabase {
        return database
    }

//    @Provides
//    @Singleton
//    fun provideCustomViewModelFactory(repository: CustomerRepository): ViewModelProvider.Factory {
//        return CustomViewModelFactory(repository)
//    }
}
