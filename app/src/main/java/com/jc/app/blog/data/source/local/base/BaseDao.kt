package com.jc.app.blog.data.source.local.base

import androidx.room.Insert
import androidx.room.OnConflictStrategy


interface BaseDao<T> {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(data: T)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(data: List<T>)
}