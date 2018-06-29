package com.quandoo.reservetables.data.model.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Query
import com.quandoo.reservetables.data.model.Table
import io.reactivex.Flowable

@Dao
interface TableDao : BaseDao<Table> {

    @get:Query("SELECT * FROM `Table` ORDER BY id ASC")
    val tableList: Flowable<List<Table>>

    @Query("SELECT * FROM `Table` WHERE id = :id")
    fun getTableById(id: Long): Flowable<Table>

}