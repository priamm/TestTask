package com.example.test.data.db

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(tableName = "employee_specialty_cross_ref", primaryKeys = ["employee_id", "specialty_id"])
data class EmployeeSpecialtyCrossRef(
    @ColumnInfo(name = "employee_id")
    val employee_id: Int,
    @ColumnInfo(name = "specialty_id")
    val specialty_id: Int
)