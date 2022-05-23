package com.nextproject.hematlistrik.model.kwh

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(entities = [Kwh::class], version = 1)
abstract class KwhDB : RoomDatabase() {
    abstract fun kwhDao(): KwhDao
}
