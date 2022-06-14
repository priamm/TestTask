package com.example.test.data.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "specialty")
data class Specialty(
    @ColumnInfo(name = "name")
    val name: String,
    @PrimaryKey
    @ColumnInfo(name = "specialty_id")
    val specialtyId: Int
)