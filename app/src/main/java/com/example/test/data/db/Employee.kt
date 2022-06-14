package com.example.test.data.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "employee")
data class Employee(
    @PrimaryKey
    @ColumnInfo(name = "employee_id")
    val employeeId: Int,
    @ColumnInfo(name = "avatr_url")
    val avatar: String?,
    @ColumnInfo(name = "birthday")
    val birthday: String?,
    @ColumnInfo(name = "f_name")
    val firstName: String?,
    @ColumnInfo(name = "l_name")
    val lastName: String?
)