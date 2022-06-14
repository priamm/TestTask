package com.example.test.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface EmployeeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(employee: List<Employee>)

    @Query("SELECT * FROM employee WHERE employee_id IN (SELECT employee_id FROM employee_specialty_cross_ref WHERE specialty_id = :specialtyId)")
    fun getListOfEmployee(specialtyId: Int): LiveData<List<Employee>>

    @Query("SELECT * FROM employee WHERE employee_id = :employeeId")
    fun getEmployee(employeeId: Int): LiveData<Employee>
}