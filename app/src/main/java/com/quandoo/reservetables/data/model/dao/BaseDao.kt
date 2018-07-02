package com.quandoo.reservetables.data.model.dao

import android.arch.persistence.room.*
import android.databinding.BaseObservable
import io.reactivex.Flowable

@Dao
interface BaseDao<T : BaseObservable> {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(t: T)

    @Update
    fun update(t: T)

    @Delete
    fun delete(t: T)
}