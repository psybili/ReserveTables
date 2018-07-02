package com.quandoo.reservetables.data.repository

import io.reactivex.Flowable

interface IRepository<T> {
    val list: Flowable<List<T>>
}
