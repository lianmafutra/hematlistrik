package com.nextproject.hematlistrik.model.kwh

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query

@Dao
interface KwhDao {
    @Query("SELECT * from kwh")
    fun getAll(): List<Kwh>

    @Insert(onConflict = REPLACE)
    fun insert(kwh: Kwh)

    @Delete
    fun delete(kwh: Kwh)
}