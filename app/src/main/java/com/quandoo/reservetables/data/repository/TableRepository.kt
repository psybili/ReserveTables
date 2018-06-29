package com.quandoo.reservetables.data.repository

import com.quandoo.reservetables.data.api.TableService
import com.quandoo.reservetables.data.model.Table
import com.quandoo.reservetables.data.model.dao.TableDao
import io.reactivex.Flowable
import javax.inject.Inject

class TableRepository @Inject constructor(
        private val tableService: TableService,
        private val tableDao: TableDao) {

    val list: Flowable<List<Table>>
        get() = tableDao.tableList

    fun create(table: Table) = tableDao.insert(table)

    fun read(id: Long): Flowable<Table> = tableDao.getTableById(id)

    fun update(table: Table) = tableDao.update(table)

    fun delete(table: Table) = tableDao.delete(table)

    fun updateTables() = tableService.updateTables()

}
