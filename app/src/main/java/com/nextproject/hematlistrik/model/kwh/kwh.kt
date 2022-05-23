package com.nextproject.hematlistrik.model.kwh

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "kwh")
data class Kwh
    (
    @ColumnInfo(name = "nama") val nama: String,
    @ColumnInfo(name = "jenis") val jenis: String,
) {
    @PrimaryKey(autoGenerate = true)
    var id = 0
}