package com.quandoo.reservetables.data.model.dao

import android.arch.persistence.room.*

@Dao
interface BaseDao<in T> {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(t: T)

    @Update
    fun update(t: T)

    @Delete
    fun delete(t: T)
}