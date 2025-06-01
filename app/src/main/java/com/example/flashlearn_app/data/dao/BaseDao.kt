package com.example.flashlearn_app.data.dao

import androidx.room.*

@Dao
interface BaseDao<T> {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(obj: T)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(objs: List<T>)

    @Update
    suspend fun update(obj: T)

    @Delete
    suspend fun delete(obj: T)
}